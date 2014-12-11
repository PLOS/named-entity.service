#!/usr/bin/env python2

__author__ = 'jgray@plos.org'

from selenium.webdriver.common.by import By
from ...Base.PlosPage import PlosPage


class Menu(PlosPage):

  """
  Model the desktop PLos site menu.
  """

  def __init__(self, driver):
    super(Menu, self).__init__(driver)

    # Locators - Instance members
    self._nav_elements = (By.ID, 'pagehdr')
    self._about_us_sublinks = (By.CSS_SELECTOR, 'ul#about-us-dropdown-list > li > a')
    self._browse_sublinks = (By.CSS_SELECTOR, 'ul#browse-dropdown-list > li > a')
    self._for_authors_sublinks = (By.CSS_SELECTOR, 'ul#for-authors-dropdown-list > li > a')

  # POM Actions

  def _retrieve_sub_links(self, by, locator):
    links = self._get(self._nav_elements).find_elements(by, locator)
    assert len(links) > 1
    return links

  def _retrieve_about_us_links(self):
    return self._retrieve_sub_links(*self._about_us_sublinks)

  def _retrieve_browse_links(self):
    return self._retrieve_sub_links(*self._browse_sublinks)

  def _retrieve_for_authors_links(self):
    return self._retrieve_sub_links(*self._for_authors_sublinks)

  def validate_about_us_links(self, expected_links):
    print 'Starting validation of "About Us" links...'
    actual_links = self._retrieve_about_us_links()
    self._validate_individual_links(actual_links, expected_links)

  def validate_browse_links(self, expected_links):
    print 'Starting validation of "Browse" links...'
    actual_links = self._retrieve_browse_links()
    self._validate_individual_links(actual_links, expected_links)

  def validate_for_authors_links(self, expected_links):
    print 'Starting validation of "For Authors" links...'
    actual_links = self._retrieve_for_authors_links()
    self._validate_individual_links(actual_links, expected_links)

  def _validate_individual_links(self, actual_links, expected_links):
    # Validations
    link_count1 = len(actual_links)
    link_count2 = len(expected_links)
    print "Actual links count is %s (Expected %s)" % (link_count1, link_count2)
    assert link_count1 == link_count2

    for link in actual_links:
      self._wait_for_element(link)
      link_text = link.text.strip()
      print 'Verifying link "%s":' % link_text,
      assert link_text in expected_links, "'%s' was not among '%s'" % (link_text, expected_links)
      print "PRESENT /",
      assert link.get_attribute('href') == expected_links[link_text], "'%s' != '%s'" % (link.get_attribute('href'), 
                                                                                        expected_links[link_text])
      print "HREF OK /",
      assert self._is_link_valid(link) is True
