#!/usr/bin/env python2

__author__ = 'jgray@plos.org'

from selenium.webdriver.common.by import By
from ...Base.PlosPage import PlosPage

# Variable definitions


class Article(PlosPage):
  """
  Model an abstract base Article page, Article Tab.
  """

  def __init__(self, driver, url_suffix=''):
    super(Article, self).__init__(driver, url_suffix)

    # Locators - Instance members
    self._article_doi = (By.ID, 'artDoi')

  # POM Actions

  def extract_page_doi(self):
    doi = self._get(self._article_doi).text.lstrip('DOI: ')
    return doi

