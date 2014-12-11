#!/usr/bin/env python2

__author__ = 'jkrzemien@plos.org'

from selenium.webdriver.common.by import By
from ...Base.PlosPage import PlosPage


class HomePage(PlosPage):

  """
  Model an abstract base Journal page.
  """

  def __init__(self, driver, urlSuffix=''):
    super(HomePage, self).__init__(driver, urlSuffix)

    # Locators - Instance members
    self._article_type_menu = (By.ID, 'article-type-menu')
    self._recent_button = (By.XPATH, ".//li[@data-method='recent']")
    self._popular_button = (By.XPATH, ".//li[@data-method='popular']")

  # POM Actions

  def click_recent_button(self):
    print 'Click Recent button'
    recent_link = self._get(self._article_type_menu).find_element(*self._recent_button)
    recent_link.click()
    return self

  def click_popular_button(self):
    print 'Click Popular button'
    popular_link = self._get(self._article_type_menu).find_element(*self._popular_button)
    popular_link.click()
    return self

