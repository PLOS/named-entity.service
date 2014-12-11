#!/usr/bin/env python2

__author__ = 'jkrzemien@plos.org'

from selenium.webdriver.common.by import By
from ...Base.PlosPage import PlosPage


class Menu(PlosPage):

  """
  Model the mobile PLoS site menu.
  """

  def __init__(self, driver):
    super(Menu, self).__init__(driver)

    # Locators - Instance members
    self._site_menu_button = (By.ID, 'site-menu-button')
    self._menu_panel = (By.ID, 'common-menu-container')
    self._about_us_link = (By.PARTIAL_LINK_TEXT, 'About Us')
    self._for_authors_link = (By.PARTIAL_LINK_TEXT, 'For Authors')
    self._about_us_sublinks = (By.CSS_SELECTOR, 'li.accordion-item.expanded > ul > li > a')
    self._for_authors_sublinks = (By.CSS_SELECTOR, 'li.accordion-item.expanded > ul > li > a')

  # POM Actions

  def click_menu_button(self):
    print 'Click Menu button'
    self._get(self._site_menu_button).click()
    return self

  def click_about_us(self):
    print 'Click About Us button'
    about_us = self._get(self._menu_panel).find_element(*self._about_us_link)
    about_us.click()
    return self

  def click_for_authors(self):
    print 'Click For Authors button'
    about_us = self._get(self._menu_panel).find_element(*self._for_authors_link)
    about_us.click()
    return self

  def _retrieve_sub_links(self, by, locator):
    links = self._get(self._menu_panel).find_elements(by, locator)
    assert len(links) > 1
    return links

  def _retrieve_about_us_links(self):
    return self._retrieve_sub_links(*self._about_us_sublinks)

  def _retrieve_for_authors_links(self):
    return self._retrieve_sub_links(*self._for_authors_sublinks)

  def validate_about_us_links(self, expectedLinks):
    print 'Starting validation of "About Us" links...'
    actualLinks = self._retrieve_about_us_links()
    self._validate_individual_links(actualLinks, expectedLinks)

  def validate_for_authors_links(self, expectedLinks):
    print 'Starting validation of "For Authors" links...'
    actualLinks = self._retrieve_for_authors_links()
    self._validate_individual_links(actualLinks, expectedLinks)


  def _validate_individual_links(self, actualLinks, expectedLinks):
    # Validations
    linkCount1 = len(actualLinks)
    linkCount2 = len(expectedLinks)
    print "Actual links count is %s (Expected %s)" % (linkCount1, linkCount2)
    assert linkCount1 == linkCount2

    for link in actualLinks:
      self._wait_for_element(link)
      linkText = link.text.strip()
      print 'Verifiying link "%s":' % linkText,
      assert linkText in expectedLinks.keys()
      print "PRESENT /",
      assert link.get_attribute('href') == expectedLinks[linkText]
      print "HREF OK /",
      assert self._is_link_valid(link) is True
