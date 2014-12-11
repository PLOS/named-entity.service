#!/usr/bin/env python2

__author__ = 'jgray@plos.org'

from selenium.webdriver.common.by import By
from ...Base.PlosPage import PlosPage


class HomePage(PlosPage):

  """
  Model an abstract base Journal page.
  """

  def __init__(self, driver, url_suffix=''):
    super(HomePage, self).__init__(driver, url_suffix)

    # Locators - Instance members
    self._nav_main = (By.ID, 'pagehdr')
    self._logo = (By.XPATH, "//li[@class='home-link']")
    self._search_widget = (By.XPATH, "//button[@type='submit']")
    self._about_us_menu = (By.ID, "about-us")
    self._browse_menu = (By.ID, "browse")
    self._for_authors_menu = (By.ID, "for-authors")

  # POM Actions

  def click_logo(self):
      logo_link = self._get(self._nav_main).find_element(*self._logo)
      logo_link.click()
      return self

  def click_search_widget(self):
      swidget_link = self._get(self._nav_main).find_element(*self._search_widget)
      swidget_link.click()
      return self

  def hover_about_us(self):
    about_us = self._get(self._about_us_menu)
    #about_us.click()
    self._actions.move_to_element(about_us).perform()
    return self

  def hover_browse(self):
    browse = self._get(self._browse_menu)
    self._actions.move_to_element(browse).perform()
    return self

  def hover_for_authors(self):
    for_authors = self._get(self._for_authors_menu)
    self._actions.move_to_element(for_authors).perform()
    return self