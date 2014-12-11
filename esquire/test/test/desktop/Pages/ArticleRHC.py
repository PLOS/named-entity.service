#!/usr/bin/env python2
# -*- coding: utf-8 -*-

__author__ = 'jgray@plos.org'

from selenium.webdriver.common.by import By
from Article import Article
import urllib
import urllib2
import re
import requests
import json
from .. import resources
import operator
import time

class ArticleRHC(Article):

  """
  Model an abstract of the right hand column of the Article page.
  """

  def __init__(self, driver, url_suffix=''):
    super(ArticleRHC, self).__init__(driver, url_suffix)

    # Locators - Instance members
    self._license = (By.ID, 'licenseShort')
    self._article_title = (By.ID, 'artTitle')
    self._article_doi = (By.ID, 'artDoi')
    self._article_right_hand_column = (By.CLASS_NAME, 'article-aside')
    self._right_hand_column_download_div = (By.CLASS_NAME, 'dload-menu')
    self._right_hand_column_download_pdf = (By.ID, 'downloadPdf')
    self._right_hand_column_download_menu = (By.CLASS_NAME, 'dload-hover')
    self._right_hand_column_download_menu_popout = (By.CLASS_NAME, 'dload-xml')
    self._right_hand_column_download_citation = (By.ID, 'downloadCitation')
    self._right_hand_column_download_xml = (By.ID, 'downloadXml')
    self._right_hand_column_print_div = (By.ID, 'printArticle')
    self._right_hand_column_print_popout = (By.CLASS_NAME, 'print-options')
    self._right_hand_column_print_local = (By.XPATH, "//li/a[@title='Print Article']")
    self._right_hand_column_print_ezreprint = (By.XPATH, "//li/a[@title='Odyssey Press']")
    self._right_hand_column_print_order_reprints = (By.XPATH, "//li/a[@title='Order Reprints']")
    self._right_hand_column_crossmark_link = (By.ID, 'open-crossmark')
    self._right_hand_column_crossmark_logo = (By.ID, 'crossmark-icon')
    self._crossmark_iframe = (By.ID, 'crossmark-dialog-frame')
    self._crossmark_iframe_doi = (By.CLASS_NAME, 'doi')
    self._crossmark_closer = (By.XPATH, '//button[@title="close"]')
    self._related_articles_div = (By.XPATH, "//div[@class='related-articles-container']")
    self._right_hand_column_share_div = (By.ID, 'shareArticle')
    self._share_reddit = (By.ID, 'shareReddit')
    self._share_google = (By.ID, 'shareGoogle')
    self._share_stumbleupon = (By.ID, 'shareStumble')
    self._share_facebook = (By.ID, 'shareFacebook')
    self._share_linkedin = (By.ID, 'shareLinkedIn')
    self._share_citeulike = (By.ID, 'shareCiteULike')
    self._share_mendeley = (By.ID, 'shareMendeley')
    self._share_pubchase = (By.ID, 'sharePubChase')
    self._share_twitter = (By.ID, 'twitter-share-link')
    self._share_email = (By.ID, 'shareEmail')
    self._twitter_hashtag = ''
    self._right_hand_column_iitfc_div = (By.CLASS_NAME, 'aside-container')
    self._right_hand_column_iitfc_link_list = (By.ID, 'collectionList')
    self._right_hand_column_iitfc_anchor = (By.XPATH, "//ul[@id='collectionList']/li/a")
    self._subject_area_div = (By.XPATH, "//div[@class='subject-areas-container']")
    self._subject_area_list = (By.ID, 'subjectList')
    self._floater_close = (By.XPATH, "//div[@class='close-floater' and @title='close']")


  # POM Actions
  def assert_right_hand_column(self):
    self._get(self._article_right_hand_column)
    return self

  def assert_download_div(self):
    self._get(self._right_hand_column_download_div)
    return self

  def assert_download_pdf_button(self):
    self._get(self._right_hand_column_download_pdf)
    return self

  def validate_download_pdf(self):
    download_pdf = self._get(self._right_hand_column_download_pdf)
    article_type_text = self._get(self._article_doi).text
    article_doi_text = article_type_text.lstrip('DOI: ')
    pdf_link = download_pdf.get_attribute('href')
    lstripped_link = re.sub(r'^.*doi/', '', pdf_link)
    rstripped_link = re.sub(r'&representation=PDF', '', lstripped_link)
    assert article_doi_text == rstripped_link
    return self

  def assert_download_menu(self):
    dl_menu = self._get(self._right_hand_column_download_menu)
    self._actions.move_to_element(dl_menu).perform()
    return self

  def validate_citation_download(self):
    article_type_text = self._get(self._article_doi).text
    article_doi_text = article_type_text.lstrip('DOI: ')
    dl_menu = self._get(self._right_hand_column_download_menu)
    self._actions.move_to_element(dl_menu).perform()
    download_cite = self._get(self._right_hand_column_download_citation)
    cite_link = download_cite.get_attribute('href')
    lstripped_link = re.sub(r'^.*citation', 'citation', cite_link)
    rstripped_link = re.sub(r'\.action.*$', '', lstripped_link)
    assert rstripped_link == 'citationList'
    lstripped_link2 = re.sub(r'^.*doi%2F', '', cite_link)
    assert lstripped_link2 == article_doi_text
    return self

  def validate_xml_download(self):
    article_type_text = self._get(self._article_doi).text
    article_doi_text = article_type_text.lstrip('DOI: ')
    dl_menu = self._get(self._right_hand_column_download_menu)
    self._actions.move_to_element(dl_menu).perform()
    download_xml = self._get(self._right_hand_column_download_xml)
    xml_link = download_xml.get_attribute('href')
    lstripped_link = re.sub(r'^.*id=', '', xml_link)
    rstripped_link = re.sub(r'\.XML$', '', lstripped_link)
    assert rstripped_link == article_doi_text
    return self

  def moveto_section_header_doi(self):
    article_doi = self._get(self._article_doi)
    self._actions.move_to_element(article_doi).perform()
    return self

  def assert_print_div(self):
    self._get(self._right_hand_column_print_div)
    return self

  def validate_local_print(self):
    print_menu = self._get(self._right_hand_column_print_div)
    self._actions.move_to_element(print_menu).perform()
    self._get(self._right_hand_column_print_local)
    return self

  def validate_ezreprint(self):
    ezreprint = ''
    order_reprints = ''
    article_type_text = self._get(self._article_doi).text
    article_doi_text = article_type_text.lstrip('DOI: ')
    article_title_text = self._get(self._article_title).text
    print_menu = self._get(self._right_hand_column_print_div)
    self._actions.move_to_element(print_menu).perform()
    try:
      ezreprint = self._get(self._right_hand_column_print_ezreprint)
    except:
      order_reprints = self._get(self._right_hand_column_print_order_reprints)
    if ezreprint:
      ezreprint_href = ezreprint.get_attribute('href')
    else:
      ezreprint_href = order_reprints.get_attribute('href')
    # validate reprint order request type
    re.search('.*(reprint_order\.php).*', ezreprint_href).group(1)
    # validate correct doi
    print("Validating that we are passing the doi to the reprint service")
    ezreprint_href_doi = re.search('.*(10.1371/journal.p.{3}.\d{7}).*', ezreprint_href).group(1)
    assert ezreprint_href_doi == article_doi_text
    # validate correct article title in order
    print("Validating that we are passing the correct parameters to the reprint service")
    ezreprint_href_title = re.search('.*title=(.*)&author_name=.*', ezreprint_href).group(1)
    ezreprint_href_title_decoded = urllib.unquote(ezreprint_href_title)
    article_title_text_decoded = urllib.unquote(article_title_text)
    print(article_title_text_decoded)
    print(ezreprint_href_title_decoded)
    assert article_title_text_decoded == ezreprint_href_title_decoded
    return self

  def validate_crossmark_link(self):
    article_type_text = self._get(self._article_doi).text
    article_doi_text = article_type_text.lstrip('DOI: ')
    crossmark_link = self._get(self._right_hand_column_crossmark_link)
    crossmark_link.click()
    # Switch to iframe
    xmark_iframe = self._crossmark_iframe
    self.traverse_to_frame(xmark_iframe[1])
    xm_doi = self._get(self._crossmark_iframe_doi).text
    xm_doi_stripped = xm_doi.lstrip('http://dx.doi.org/')
    assert xm_doi_stripped == article_doi_text
    # switch to default context
    self.traverse_from_frame()
    closer = self._get(self._crossmark_closer)
    closer.click()
    return self

  def assert_crossmark_logo(self):
    self._get(self._right_hand_column_crossmark_logo)
    return self

  def validate_related_articles(self):
    # get the doi
    article_type_text = self._get(self._article_doi).text
    article_doi_text = article_type_text.lstrip('DOI: ')

    # make a call to rhino to get the related article information
    response = requests.get(resources.rhino_url + 'articles/' + article_doi_text)
    json_response = json.loads(response.text)
    related_articles = json_response['relatedArticles']

    if (len(related_articles) == 0):
      # check to see if there is any related articles
      should_exist = True
      self.set_timeout(1)

      try:
        self._get(self._related_articles_div)
      except:
        should_exist = False

      self.restore_timeout()

      # related articles div should not exist
      assert should_exist == False

    else:
      # check to make sure that the related articles in the rhino response matches up with the
      # related articles in the related articles div

      # get the related article links from the page
      div = self._get(self._related_articles_div)
      related_article_links = div.find_elements_by_tag_name("a")

      assert len(related_articles) == len(related_article_links)
      if (len(related_articles) == len(related_article_links)):

        for related_article in related_articles:
          found = False
          for related_article_link in related_article_links:
            # http://stackoverflow.com/questions/753052/strip-html-from-strings-in-python
            # picked a simpler solution in the thread
            related_article['title'] = re.sub(r'(<!--.*?-->|<[^>]*>)', '', related_article['title'])

            # check the article title and the doi in the link
            if (related_article['title'].strip() == related_article_link.text.strip()) and \
                (related_article['doi'] in related_article_link.get_attribute("href")):
              found = True
              break

          assert found == True

    return self

  def assert_share_menu(self):
    share_div = self._get(self._right_hand_column_share_div)
    self._actions.move_to_element(share_div).perform()

    self._get(self._share_reddit)
    self._get(self._share_google)
    self._get(self._share_stumbleupon)
    self._get(self._share_facebook)
    self._get(self._share_linkedin)
    self._get(self._share_citeulike)
    self._get(self._share_mendeley)
    self._get(self._share_pubchase)
    self._get(self._share_twitter)
    self._get(self._share_email)

    return self

  def validate_share_menu_items(self):
    share_div = self._get(self._right_hand_column_share_div)
    self._actions.move_to_element(share_div).perform()

    doi = self._get(self._article_doi).text.lstrip('DOI: ')
    title = self._get(self._article_title).text

    escaped_doi_url = urllib.quote_plus("http://dx.doi.org/" + doi)
    title = re.sub(r'<[^>]*>', '', title)
    escaped_title = urllib.pathname2url(title)

    # validate the url

    # reddit
    expected_link = "http://www.reddit.com/submit?url=" + escaped_doi_url
    actual_link = self._get(self._share_reddit).get_attribute("href")
    # print (expected_link)
    # print (actual_link)
    assert actual_link == expected_link

    # google+
    expected_link = "https://plus.google.com/share?url=" + escaped_doi_url
    actual_link = self._get(self._share_google).get_attribute("href")
    # print (expected_link)
    # print (actual_link)
    assert actual_link == expected_link

    # stumble upon
    expected_link = "http://www.stumbleupon.com/submit?url=" + escaped_doi_url
    actual_link = self._get(self._share_stumbleupon).get_attribute("href")
    # print (expected_link)
    # print (actual_link)
    assert actual_link == expected_link

    # facebook
    partial_expected_link = "http://www.facebook.com/share.php?u=" + escaped_doi_url
    actual_link = self._get(self._share_facebook).get_attribute("href")
    # print (partial_expected_link)
    # print (actual_link)
    assert partial_expected_link in actual_link

    decoded_actual_link = urllib.unquote(actual_link)
    assert title in decoded_actual_link

    # linkedin
    partial_expected_link = "http://www.linkedin.com/shareArticle?url=" + escaped_doi_url
    actual_link = self._get(self._share_linkedin).get_attribute("href")
    # print (expected_link)
    # print (actual_link)
    assert partial_expected_link in actual_link

    partial_text = "&summary=Checkout%20this%20article%20I%20found%20at%20PLOS"
    assert partial_text in actual_link

    decoded_actual_link = urllib.unquote(actual_link)
    assert title in decoded_actual_link

    # citeulike
    partial_expected_link = "http://www.citeulike.org/posturl?url=" + escaped_doi_url
    actual_link = self._get(self._share_citeulike).get_attribute("href")
    # print (expected_link)
    # print (actual_link)
    assert partial_expected_link in actual_link

    decoded_actual_link = urllib.unquote(actual_link)
    assert title in decoded_actual_link

    # mandeley
    expected_link = "http://www.mendeley.com/import/?url=" + escaped_doi_url
    actual_link = self._get(self._share_mendeley).get_attribute("href")
    # print (expected_link)
    # print (actual_link)
    assert actual_link == expected_link

    # pubchase
    expected_link = "https://www.pubchase.com/library?add_aid=" + doi + "&source=plos"
    actual_link = self._get(self._share_pubchase).get_attribute("href")
    # print (expected_link)
    # print (actual_link)
    assert actual_link == expected_link

    # twitter
    partial_expected_link = "http://twitter.com/intent/tweet?url=" + escaped_doi_url
    actual_link = self._get(self._share_twitter).get_attribute("href")
    # print (partial_expected_link)
    # print (actual_link)
    assert partial_expected_link in actual_link

    partial_title = self._twitter_hashtag + ': ' + title
    partial_title = partial_title[:100]
    decoded_actual_link = urllib.unquote(actual_link)

    # print (partial_title)
    # print (decoded_actual_link)
    assert partial_title in decoded_actual_link

    # email
    partial_email_link = "/article/email/info%3Adoi%2F" + urllib.quote_plus(doi)
    actual_link = self._get(self._share_email).get_attribute("href")
    # print (partial_email_link)
    # print (actual_link)
    assert partial_email_link in actual_link

  def validate_included_in_the_following_collection(self):
    iitfc_links_from_rhino = []
    iitfc_links_text = []
    coll_issues = {}
    coll_issues_sorted = []
    relevant_issue = []
    # Figure out if an article has a related collection and if so, then:
    doi = Article.extract_page_doi(self)
    print("Calling Rhino api v1 to get article collection information...")
    response = requests.get(resources.rhino_url + 'articles/' + doi)
    json_response = json.loads(response.text)
    has_issues_from_rhino = json_response['issues']
    if not has_issues_from_rhino:
      return self
    else:
      for issue in has_issues_from_rhino:
        issue_title = json_response['issues'][issue]['parentJournal']['title']
        if issue_title == 'PLOS Collections':
          relevant_issue.append(issue)
      # Next look at the rhino response to isolate the collection info
      for issue in relevant_issue:
        issue_pub_date = json_response['issues'][issue]['created']
        rhino_link_unstripped = json_response['issues'][issue]['displayName']
        rhino_link_clean = Article.get_text(self, rhino_link_unstripped)
        #Create a dictionary entry containing title and pub date
        coll_issues[rhino_link_clean] = issue_pub_date
      if relevant_issue:
        assert self._get(self._right_hand_column_iitfc_div)
        # If the div exists there must be a link_list
        assert self._get(self._right_hand_column_iitfc_link_list)
        # There should be always at least one link, but possibly more. They
        # should be ordered by creation date
        # Get links from the page and put into a list in order
        iitfc_links = self._gets(self._right_hand_column_iitfc_anchor)
        for link in iitfc_links:
          iitfc_links_text.append(link.text)
      # sort the dictionary entries into a list based on pub date
      coll_issues_sorted = sorted(coll_issues.items(), key=operator.itemgetter(1), reverse=True)
      # unwind the title from the sorted list based on the dictionary
      for link in coll_issues_sorted:
        link_pub = str(link)
        link_title = link_pub.split(',')[0]
        clean_link_title = link_title[3:-1]
        iitfc_links_from_rhino.append(clean_link_title)
      #print("Links from Rhino: " + str(iitfc_links_from_rhino))
      #print("Links from Page: " + str(iitfc_links_text))
      assert iitfc_links_text == iitfc_links_from_rhino

    return self

  def assert_subject_areas(self):
    self._get(self._subject_area_div)
    return self

  def validate_subject_areas(self):
    subject_area_div = self._get(self._subject_area_div)

    # verify the "Subject Areas" heading
    header = subject_area_div.find_element_by_tag_name("h3")
    assert "Subject Areas" in header.text

    # verify the "?" button
    subj_info_div = subject_area_div.find_element_by_id("subjInfo")
    assert subj_info_div.text == "?"

    # verify mouse hover over action
    self._actions.move_to_element(subj_info_div).perform()
    subj_info_text_div = subject_area_div.find_element_by_id("subjInfoText")
    info_text = "We want your feedback. Do these Subject Areas make sense for this article? Click the target next to the incorrect Subject Area and let us know. Thanks for your help!"
    assert info_text in subj_info_text_div.text

    info_text = "For more information about PLOS Subject Areas, click"
    assert info_text in subj_info_text_div.text

    helpful_link = subject_area_div.find_element_by_tag_name("a")
    actual_link = helpful_link.get_attribute("href")
    # TODO get journal specific link domain to build the full expected url
    expected_link = "static/help#subjectAreas"
    assert expected_link in actual_link

    # close the pop up
    self._actions.move_to_element(self._get(self._right_hand_column_crossmark_logo)).perform()

    # verify that the subject areas are sorted in the weight order
    article_doi_text = self._get(self._article_doi).text
    article_doi_text = article_doi_text.lstrip('DOI: ')

    response = requests.get(resources.rhino_url + 'articles/' + article_doi_text)
    json_response = json.loads(response.text)
    categories = json_response['categories']

    collapsed_categories = {}
    for category in categories:
      index = category['path'].rfind('/') + 1
      short_category = category['path'][index:]
      if not short_category in collapsed_categories:
        collapsed_categories[short_category] = category['weight']

    expected_categories = sorted(collapsed_categories.items(), key=operator.itemgetter(1), reverse=True)

    subject_area_list = self._get(self._subject_area_list)
    subject_area_links = subject_area_list.find_elements_by_tag_name('a')
    actual_categories = list(map((lambda sb_link: sb_link.text), subject_area_links))

    assert len(actual_categories) == len(expected_categories)

    array_index = 0
    for e_category in expected_categories:
      # print "expected category", e_category[0]
      # print "actual   category", actual_categories[array_index]
      assert e_category[0] == actual_categories[array_index]
      array_index = array_index + 1

    # verify that when you click on the subject area, it takes you to the search results page
    for sa_link in subject_area_links:
      # TODO add the journal link to the expected_href so that it can be a string comparsion vs string in test
      expected_href = "/search/advanced?unformattedQuery={}".format(urllib.pathname2url("subject:\"{}\"".format(sa_link.text)))
      # print expected_href
      assert expected_href in sa_link.get_attribute("href")

      response = urllib2.urlopen(sa_link.get_attribute("href"))
      assert 200 == response.getcode()

    # verify that when you click on the button, a pop up message shows up
    # verify the pop up message
    # verify the yes and no action
    subject_area_lis = subject_area_list.find_elements_by_tag_name('li')

    saw_article_floating_header = False
    index = 0
    for sa_li in subject_area_lis:

      # while going through the subject areas, the article floating header will kick in eventually
      # when that happens, close it.
      if not saw_article_floating_header:
        self.set_timeout(1)
        try:
          (self._get(self._floater_close)).click()
          saw_article_floating_header = True
        except:
          pass

        self.restore_timeout()
      else:
        # another thing to keep in mind that the pop up stays open for a little bit after the user
        # clicks yes or no
        time.sleep(1)

      sa_link = sa_li.find_element_by_tag_name("a")
      # verify that when you hover over the button, it changes state
      self._actions.move_to_element(sa_link).perform()
      b_color = sa_link.value_of_css_property("background-color")
      # #3c63af
      assert b_color == 'rgba(60, 99, 175, 1)'

      # TODO: verify that when you hover over the circle, it changes state
      # need to capture the image change

      sa_circle = sa_li.find_element_by_tag_name("span")
      sa_circle.click()

      popup_text = (sa_li.find_element_by_class_name("taxo-explain")).text

      # print "popup:", popup_text

      assert "Is the Subject Area" in popup_text
      assert "\"{}\"".format(sa_link.text) in popup_text
      assert "applicable to this article?".format(sa_link.text) in popup_text

      yes_button = sa_li.find_element_by_id("noFlag")
      no_button = sa_li.find_element_by_id("flagIt")

      if index % 2 == 0:
        yes_button.click()
      else:
        # TODO: verify that the change was made in the db?
        no_button.click()

      index = index + 1

    # refresh the page
    self.refresh()

    # check to make sure that clicked circle state has been retained.
    subject_area_list = self._get(self._subject_area_list)
    subject_area_lis = subject_area_list.find_elements_by_tag_name('li')
    for sa_li in subject_area_lis:
      sa_circle = sa_li.find_element_by_tag_name("span")

      if index % 2 == 0:
        assert 'taxo-flag' == sa_circle.get_attribute('class')
      else:
        assert 'taxo-flag flagged' == sa_circle.get_attribute('class')

      index = index + 1

    return self