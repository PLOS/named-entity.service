#!/usr/bin/env python2

__author__ = 'jgray@plos.org'

from selenium.webdriver.common.by import By
from Article import Article
from ...Base import ParseXML
import re
import requests
import json
from .. import resources

# Variable definitions


class ArticleBody(Article):
  """
  Model an abstract base Article page, Article Tab.
  """

  def __init__(self, driver, url_suffix=''):
    super(ArticleBody, self).__init__(driver, url_suffix)

    # Locators - Instance members
    self._article_doi = (By.ID, 'artDoi')
    self._tab_article = (By.ID, 'tabArticle')
    self._article_body_container = (By.CLASS_NAME, 'article-container')
    self._article_navigation_div = (By.ID, 'nav-article')
    self._article_dynamic_nav = (By.CLASS_NAME, 'nav-page')
    self._article_nav_amendments = (By.CLASS_NAME, 'amendment-correction')
    self._article_dynamic_headings = (By.CLASS_NAME, 'scroll')
    self._article_fixed_nav = (By.CLASS_NAME, 'nav-secondary')
    self._article_nav_comments = (By.ID, 'nav-comments')
    self._article_nav_media = (By.ID, 'nav-media')
    self._article_nav_figures = (By.ID, 'nav-figures')
    self._article_metadata = (By.CLASS_NAME, 'articleinfo')
    self._article_body_headings = (By.TAG_NAME, 'h2')
    self._article_body_amendment_notice_div = (By.CLASS_NAME, 'amendment')
    self._article_body_amendment_notice_corrections = (By.XPATH,
                                                       '//div[@class="amendment amendment-correction toc-section"]/h2')
    self._article_body_amendment_notice_eoc = (By.XPATH, '//div[@class="amendment amendment-eoc toc-section"]/h2')
    self._article_body_amendment_notice_retraction = (By.XPATH,
                                                      '//div[@class="amendment amendment-retraction toc-section"]/h2')
    self._article_body_amendment_notice_text = (By.CLASS_NAME, 'amendment-body')
    self._article_body_amendment_notice_citation = (By.CLASS_NAME, 'amendment-citation')
    self._article_body_amendment_notice_date = (By.CLASS_NAME, 'amendment-date')
    self._article_body_amendment_notice_link = (By.CLASS_NAME, 'amendment-link')

  # POM Actions

  def assert_article_tab_active(self):
    assert self._get(self._tab_article).get_attribute('class')
    return self

  def extract_page_doi(self):
    doi = self._get(self._article_doi).text.lstrip('DOI: ')
    return doi

  def validate_article_body_nav_headings(self):
    doi = ArticleBody.extract_page_doi(self)
    expression_of_concern = False
    retraction = False
    correction_count = 0
    complete_article_nav_headings = []
    # make a call to rhino to get the related article of types [correction-forward, expressed-concern, retraction ]
    # information
    print("Calling Rhino api v1 to get article amendment information...")
    response = requests.get(resources.rhino_url + 'articles/' + doi)
    json_response = json.loads(response.text)
    related_articles = json_response['relatedArticles']
    x = len(related_articles) - 1
    while x > -1:
      type_ra = json_response['relatedArticles'][x]['type']
      if type_ra in 'expressed-concern':
        expression_of_concern = True
        break
      if type_ra in 'retraction':
        retraction = True
        break
      if type_ra in 'correction-forward':
        correction_count += 1
      x -= 1

    if expression_of_concern:
      complete_article_nav_headings.append('Expression of Concern')
    if retraction:
      complete_article_nav_headings.append('Retraction')
    if correction_count > 0:
      if correction_count == 1:
        complete_article_nav_headings.append('Correction (' + str(correction_count) + ')')
      else:
        complete_article_nav_headings.append('Corrections (' + str(correction_count) + ')')

    article_nav_headings = ParseXML.ParseXML().get_article_sections('corpus', doi)
    for heading in article_nav_headings:
      complete_article_nav_headings.append(heading)
    try:
      article_presented_nav_headings = self._gets(self._article_dynamic_headings)
    except:
      print("No article headings found for " + doi)
      return (self)
    presented_nav_headings_text = []
    for element in article_presented_nav_headings:
      presented_nav_headings_text.append(element.text)
    assert presented_nav_headings_text == complete_article_nav_headings
    return self

  def validate_article_comments_number(self):
    doi = ArticleBody.extract_page_doi(self)
    # make a call to rhino to get the article comments information
    print("Calling Rhino api v1 to get article media coverage information...")
    response = requests.get(resources.rhino_url + 'articles/' + doi + '?comments')
    json_response = json.loads(response.text)
    number_of_comments_from_rhino = len(json_response)

    # get the number of comments from the page
    comments_from_page = self._get(self._article_nav_comments)
    number_of_comments_from_page = re.search('Reader Comments \((\d+)\)', comments_from_page.text)
    # check to make sure that the number of comments in the rhino response matches up with the
    # number indicated in the left hand nav
    assert number_of_comments_from_page.group(1) == str(number_of_comments_from_rhino)
    return self

  def validate_media_curation_link_number(self):
    alm_acc_data = ''
    doi = ArticleBody.extract_page_doi(self)
    # make a call to ALM to get the articlecoveragecurated information
    print("Calling ALM api v5 to get article media coverage information...")
    response = requests.get(resources.alm_url + 'api/v5/articles?api_key=' + resources.alm_key + '&ids=' + doi)
    json_response = json.loads(response.text)
    alm_sources = json_response['data'][0]['sources']
    # I know the following "method" is unconventional, but the MC data is the penultimate
    # source in the list so this seems more efficient even if it looks odd
    x = len(alm_sources) - 1
    while x > -1:
      alm_source = json_response['data'][0]['sources'][x]['name']
      if alm_source == 'articlecoveragecurated':
        alm_acc_data = json_response['data'][0]['sources'][x]['metrics']['total']
        #print("ALM Media Curation total for article is " + str(alm_acc_data))
        break
      x -= 1

    # get the number of comments from the page
    media_from_page = self._get(self._article_nav_media)
    number_of_media_from_page = re.search('Media Coverage \((\d+)\)', media_from_page.text)
    # check to make sure that the number of media in the alm response matches up with the
    # number indicated in the left hand nav
    assert number_of_media_from_page.group(1) == str(alm_acc_data)
    return self

  def validate_figures_link(self):
    assert self._get(self._article_nav_figures)
    return self

  def validate_article_body_headings(self):
    complete_article_headings = []
    presented_article_headings = []
    doi = ArticleBody.extract_page_doi(self)
    article_headings = ParseXML.ParseXML().get_article_sections('corpus', doi)
    try:
      article_presented_headings = self._gets(self._article_body_headings)
    except:
      print("No headings found in article " + doi)
      return self
    for aheading in article_presented_headings:
      presented_article_headings.append(aheading.text)
    for heading in article_headings:
      complete_article_headings.append(heading)
      assert heading in presented_article_headings
    return self

  def validate_article_amendment_notice(self):
    expressed_concern_doi_dirty = ''
    retraction_doi_dirty = ''
    correction_doi_list = []
    eoc_retr_background_color = 'rgba(252, 226, 229, 1)'
    eoc_retr_heading_color = 'rgba(229, 51, 80, 1)'
    corr_background_color = 'rgba(239, 239, 239, 1)'
    corr_heading_color = 'rgba(51, 51, 51, 1)'
    doi = ArticleBody.extract_page_doi(self)
    expression_of_concern = False
    retraction = False
    correction_count = 0
    # make a call to rhino to get the related article of types [correction-forward, expressed-concern, retraction ]
    # information
    print("Calling Rhino api v1 to get article amendment information...")
    response = requests.get(resources.rhino_url + 'articles/' + doi)
    json_response = json.loads(response.text)
    related_articles = json_response['relatedArticles']
    x = len(related_articles) - 1
    while x > -1:
      type_ra = json_response['relatedArticles'][x]['type']
      if type_ra in 'expressed-concern':
        expression_of_concern = True
        expressed_concern_doi_dirty = json_response['relatedArticles'][x]['doi']
        break
      if type_ra in 'retraction':
        retraction = True
        retraction_doi_dirty = json_response['relatedArticles'][x]['doi']
        break
      if type_ra in 'correction-forward':
        correction_count += 1
        correction_doi_list.append(json_response['relatedArticles'][x]['doi'])
      x -= 1

    if expression_of_concern:
      amendment_div = self._get(self._article_body_amendment_notice_div)
      eoc_notice = self._get(self._article_body_amendment_notice_eoc)
      assert str(amendment_div.value_of_css_property("background-color")) == eoc_retr_background_color
      assert str(eoc_notice.value_of_css_property("color")) == eoc_retr_heading_color
      # For all but the doi here, I am not validating the content
      assert self._get(self._article_body_amendment_notice_text)
      assert self._get(self._article_body_amendment_notice_date)
      assert self._get(self._article_body_amendment_notice_citation)
      amendment_citation = self._get(self._article_body_amendment_notice_citation).text
      expressed_concern_doi = expressed_concern_doi_dirty[9:]
      assert expressed_concern_doi in amendment_citation
      assert self._get(self._article_body_amendment_notice_link)

    if retraction:
      # Validate that block is present
      amendment_div = self._get(self._article_body_amendment_notice_div)
      retraction_notice = self._get(self._article_body_amendment_notice_retraction)
      assert str(amendment_div.value_of_css_property("background-color")) == eoc_retr_background_color
      assert str(retraction_notice.value_of_css_property("color")) == eoc_retr_heading_color
      # For all but the doi here, I am not validating the content
      assert self._get(self._article_body_amendment_notice_text)
      assert self._get(self._article_body_amendment_notice_date)
      assert self._get(self._article_body_amendment_notice_citation)
      amendment_citation = self._get(self._article_body_amendment_notice_citation).text
      retraction_doi = retraction_doi_dirty[9:]
      assert retraction_doi in amendment_citation
      assert self._get(self._article_body_amendment_notice_link)

    if correction_count > 0:
      # Validate that block is present
      amendment_div = self._get(self._article_body_amendment_notice_div)
      correction_notice = self._get(self._article_body_amendment_notice_corrections)
      assert str(amendment_div.value_of_css_property("background-color")) == corr_background_color
      assert str(correction_notice.value_of_css_property("color")) == corr_heading_color
      corrections_from_page = self._gets(self._article_body_amendment_notice_citation)
      count = 1
      for correction in corrections_from_page:
        self._article_body_amendment_citation = (By.XPATH, '//div[@class="amendment-citation"][' + str(count) + ']')
        citation = self._get(self._article_body_amendment_citation).text
        dirty_doi_from_correction = re.sub(r'^.*doi: ', '', citation)
        doi_from_correction = re.sub(r' View correction.*$', '', dirty_doi_from_correction)
        self._article_body_amendment_correction_date = (By.XPATH,
                                                        '//div[@class="amendment-citation"][' + str(count) + ']/p/span[@class="amendment-date"]')
        self._article_body_amendment_correction_link = (By.XPATH,
                                                        '//div[@class="amendment-citation"][' + str(count) + ']/p/a[@class="amendment-link"]')
        assert self._get(self._article_body_amendment_correction_date)
        assert self._get(self._article_body_amendment_correction_link)
        assert doi_from_correction in str(correction_doi_list)
        count += 1
    return self