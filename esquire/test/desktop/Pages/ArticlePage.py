#!/usr/bin/env python2

__author__ = 'jgray@plos.org'

from selenium.webdriver.common.by import By
from Article import Article
from ...Base.MySQL import MySQL
from ...Base import ParseXML

# Variable definitions
author_list_truncation_max = 14
author_list_ellipsis_index = 13
author_list_ellipsis = ' [ ... ]'


class ArticlePage(Article):

  """
  Model an abstract base Article page.
  """

  def __init__(self, driver, url_suffix=''):
    super(ArticlePage, self).__init__(driver, url_suffix)

    # Locators - Instance members
    self._article_top = (By.ID, 'topslot')
    self._license = (By.ID, 'licenseShort')
    self._peer_reviewed = (By.ID, 'peerReviewed')
    self._article_type = (By.ID, 'artType')
    self._article_title = (By.ID, 'artTitle')
    self._article_author_list = (By.CLASS_NAME, 'author-list')
    self._article_author_info = (By.CLASS_NAME, 'author-info')
    self._article_author_corresponding_author = (By.CLASS_NAME, 'email')
    self._article_author_equal_contributing_author = (By.CLASS_NAME, 'contribute')
    self._article_author_customfootnote_author = (By.CLASS_NAME, 'rel-footnote')
    # inconsistent number in paired ID's - watch out
    self._article_list_expander = (By.ID, 'authors-show')
    self._article_list_contractor = (By.ID, 'author-hide')
    self._article_publication_date = (By.ID, 'artPubDate')
    self._article_doi = (By.ID, 'artDoi')
    self._article_published_in = (By.XPATH, "//ul[@class='date-doi']/li[3]/a")
    # We cross-publish to both sites (Collections) and journals (eg Medicine). Only journals are italicized
    self._article_published_in_journal = (By.XPATH, "//ul[@class='date-doi']/li[3]/a/em")
    self._article_tabs = (By.ID, 'article-tabs')
    self._tab_article = (By.ID, 'tabArticle')
    self._tab_authors = (By.ID, 'tabAuthors')
    self._tab_metrics = (By.ID, 'tabMetrics')
    self._tab_comments = (By.ID, 'tabComments')
    self._tab_related = (By.ID, 'tabRelated')
    self._section1 = (By.ID, 'section1')
    # a down page target to bring up the floating header, visible
    self._article_meta_data = (By.CLASS_NAME, 'articleinfo')
    self._section2 = (By.ID, 'section2')
    self._references_section = (By.ID, 'references')
    self._page_footer = (By.ID, 'pageftr')
    self._article_floating_title_div = (By.CLASS_NAME, 'topVisible')
    self._article_floating_title = (By.XPATH, "//div[@class='float-title-inner']/h1")
    self._article_floating_title_author_list = (By.ID, 'floatAuthorList')
    self._article_floating_title_logo = (By.XPATH, "//div[@id='titleTopCloser']/img")
    self._article_floating_title_closer = (By.CLASS_NAME, 'close-floater')

  # POM Actions

  def assert_license(self):
    """
    This method validates the presence of the OpenAccess 'bug' on article pages of
    type Research Article.


    :return:
    """
    self._get(self._license)
    return self

  def assert_peer_reviewed(self):
    self._get(self._peer_reviewed)
    return self

  def assert_article_type(self):
    self._get(self._article_type)
    return self

  def return_article_type(self):
    article_type_text = self._get(self._article_type).text
    return article_type_text

  def assert_article_title(self):
    self._get(self._article_title)
    return self

  def return_article_title(self):
    article_title_text = self._get(self._article_title).text
    article_doi_stripped = self._get(self._article_doi).text.lstrip('DOI: ')
    article_title_db = str(MySQL().query("SELECT title FROM article WHERE doi = 'info:doi/" + str(article_doi_stripped)
                                         + "'")[0]).lstrip("('").rstrip("',)")
    #italic_title_element = re.sub(r'^.*<italic>(.*)</italic>.*$', '\\1', article_title_db)
    if article_title_text != article_title_db:
        print(article_title_text + " is not equal to " + article_title_db)
    return article_title_text

  def validate_article_author_list(self):
    doi = Article.extract_page_doi(self)
    author_list = ParseXML.ParseXML().get_auths('corpus', doi)
    #print(str(author_list))
    #print(len(author_list))
    if len(author_list) > 0:
      return True
    else:
      print("Article: " + doi + " has an empty author list.")
      return False

  def assert_article_author_list(self):
    self._get(self._article_author_list)
    return self

  def validate_article_author_list_truncation(self):
    article_author_list_text = self._get(self._article_author_list).text
    if article_author_list_text == '':
      print('Article Author List is empty')
      return article_author_list_text
    else:
      author_list = article_author_list_text.split(', ')
      # /DesktopPlosGenetics/article?id=10.1371/journal.pgen.1003500 has 265 authors!
      # if article has more than 14 authors, item 13 in the list should be an ellipsis in square brackets
      if (len(author_list)) > author_list_truncation_max:
        assert author_list[author_list_ellipsis_index] == author_list_ellipsis
    return self

  def click_article_author_list_expander_contractor(self):
    article_author_list_text = self._get(self._article_author_list).text
    author_list = article_author_list_text.split(',\n')
    if (len(author_list)) > author_list_truncation_max:
      print(author_list[author_list_ellipsis_index])
      assert author_list[author_list_ellipsis_index] == author_list_ellipsis
      self._get(self._article_list_expander).click()
      # for some unknown reason the expanded section of the author list has a different class
      expanded_article_author_list_text = self._get(self._article_author_list).text
      expanded_author_list = expanded_article_author_list_text.split(',\n')
      assert expanded_author_list[author_list_ellipsis_index] != author_list_ellipsis
      self._get(self._article_list_contractor).click()
      article_author_list_text = self._get(self._article_author_list).text
      author_list = article_author_list_text.split(',\n')
      assert author_list[author_list_ellipsis_index] == author_list_ellipsis
    return self 

  def validate_article_author_list_click_tooltip(self):
    self.moveto_author_list()
    article_author_list_text = self._get(self._article_author_list).text
    if article_author_list_text == '':
      print('Article Author List is empty')
      return article_author_list_text
    else:
      author_list = article_author_list_text.split(',\n')
      if (len(author_list)) > author_list_truncation_max:
        self._get(self._article_list_expander).click()
        expanded_article_author_list_text = self._get(self._article_author_list).text
        author_list = expanded_article_author_list_text.split(',\n')
      for author in author_list:
        auth_index = author_list.index(author)
        #LOCATOR
        self._article_author_list_item = (By.XPATH, '//*[@data-author-id=' + str(auth_index) + ']')
        #LOCATOR
        #self._article_author_no_author_data = (By.XPATH, '//span[@data-author-id=' + str(auth_index) + ']')
        if self._get(self._article_author_list_item):
          self._get(self._article_author_list_item).click()
          #LOCATOR
          try:
            article_author_affiliation = (By.ID, 'authAffiliations-' + str(auth_index))
            self._get(article_author_affiliation)
            self._article_author_tooltip_close = (By.ID, 'tooltipClose' + str(auth_index))
          except:
            print("Class is no-author-data")
            continue
    outside_link = self._get(self._license)
    outside_link.click()
    if (len(author_list)) > author_list_truncation_max:
        self._get(self._article_list_contractor).click()
    return self

  def validate_special_auth(self, special_author_type):
    self.moveto_author_list()
    clean_author_list = []
    doi = Article.extract_page_doi(self)
    if special_author_type == "corresp":
      special_author_case = ParseXML.ParseXML().get_corresp_auths('corpus', doi)
      special_author_locator = self._article_author_corresponding_author
    elif special_author_type == "cocontrib":
      special_author_case = ParseXML.ParseXML().get_cocontributing_auths('corpus', doi)
      special_author_locator = self._article_author_equal_contributing_author
    elif special_author_type == "customfootnote":
      special_author_case = ParseXML.ParseXML().get_customfootnote_auths('corpus', doi)
      special_author_locator = self._article_author_customfootnote_author
    if special_author_case:
      article_author_list_text = self._get(self._article_author_list).text
      author_list = article_author_list_text.split(',\n')
      if (len(author_list)) > author_list_truncation_max:
        self._get(self._article_list_expander).click()
        expanded_article_author_list_text = self._get(self._article_author_list).text
        author_list = expanded_article_author_list_text.split(',\n')
      for author in author_list:
        grey_author = author.rstrip(' ')
        clean_author = grey_author.replace('\n[ view less ]', '')
        clean_author_list.append(clean_author)
      for author in special_author_case:
        print("Checking " + author + " for " + special_author_type + " author stanza in tooltip")
        auth_index = clean_author_list.index(author)
        #LOCATOR
        self._article_author_list_item = (By.XPATH, '//a[@data-author-id=' + str(auth_index) + ']')
        self._get(self._article_author_list_item).click()
        #LOCATOR
        self._article_author_meta_div = (By.ID, 'author-meta-' + str(auth_index))
        self._get(self._article_author_meta_div).find_element(*special_author_locator)
      if (len(author_list)) > author_list_truncation_max:
        self._get(self._article_list_contractor).click()
    outside_link = self._get(self._license)
    outside_link.click()
    return self

  def assert_article_publication_date(self):
    self._get(self._article_publication_date)
    return self

  def return_article_publication_date(self):
    article_pub_date_text = self._get(self._article_publication_date).text
    return article_pub_date_text

  def assert_article_doi(self):
    self._get(self._article_doi)
    return self

  def return_article_doi(self):
    article_doi_text = self._get(self._article_doi).text
    return article_doi_text

  def assert_article_published_in(self):
    self._get(self._article_published_in)
    return self

  def return_article_published_in(self):
    xp_journal = self._get(self._article_published_in).text
    return xp_journal

  def assert_article_published_in_journal(self):
    self._get(self._article_published_in_journal)
    return self

  def assert_tab_article(self):
    self._get(self._tab_article)
    return self

  def assert_tab_article_active(self):
    tab_article = self._get(self._tab_article)
    assert tab_article.get_attribute('class') == 'tab-title active'
    print('Article Tab is Active')
    return self 

  def assert_tab_authors(self):
    self._get(self._tab_authors)
    return self

  def assert_tab_metrics(self):
    self._get(self._tab_metrics)
    return self

  def assert_tab_comments(self):
    self._get(self._tab_comments)
    return self

  def assert_tab_related(self):
    self._get(self._tab_related)
    return self

  def moveto_top(self):
      top = self._get(self._article_top)
      self._actions.move_to_element(top).perform()
      return self

  def moveto_authors(self):
      authors = self._get(self._article_author_list)
      self._actions.move_to_element(authors).perform()
      return self

  def moveto_section_introduction(self):
      section1 = self._get(self._section1)
      self._actions.move_to_element(section1).perform()
      return self

  def moveto_author_list(self):
      self.moveto_footer()
      self.moveto_authors()
      return self

  def moveto_section_metadata(self):
    metadata = self._get(self._article_meta_data)
    self._actions.move_to_element(metadata).perform()
    return self

  #TODO: Validate not present on load, Insert Page down keystroke, validate presence
  #TODO: Validate Title, validate author list, validate logo, validate close function
  def moveto_section_methods(self):
    section2 = self._get(self._section2)
    self._actions.move_to_element(section2).perform()
    return self

  def moveto_section_references(self):
    refs = self._get(self._references_section)
    self._actions.move_to_element(refs).perform()
    return self

  def moveto_footer(self):
    footer = self._get(self._page_footer)
    self._actions.move_to_element(footer).perform()
    return self

  def assert_floating_header(self):
    floating_header = self._get(self._article_floating_title_div)
    assert floating_header
    return self

  def assert_floating_header_title(self):
    assert self._get(self._article_floating_title).text
    return self

  def assert_floating_header_author_list(self):
    doi = Article.extract_page_doi(self)
    author_list = ParseXML.ParseXML().get_auths('corpus', doi)
    if len(author_list) > 0:
      assert self._get(self._article_floating_title_author_list).text
    return self

  def assert_floating_header_logo(self):
    floater_logo = self._get(self._article_floating_title_logo)
    assert floater_logo
    return self

  def click_floating_header_closer(self):
    floater_closer = self._get(self._article_floating_title_closer)
    floater_closer.click()
    return self
