#!/usr/bin/env python2

__author__ = 'jkrzemien@plos.org'

from ...Base import Config as Config
from HomePage import HomePage
from Menu import Menu


class PlosGeneticsHomePage(HomePage):

  """
  Model the PLoS Genetics Journal page.
  """

  PROD_URL = 'http://www.plosgenetics.org/'

  def __init__(self, driver):
    super(PlosGeneticsHomePage, self).__init__(driver, '/MobilePlosGenetics/')

    # POM - Instance members
    self._menu = Menu(driver)

    # Locators - Instance members

  # POM Actions

  def click_menu_button(self):
      self._menu.click_menu_button()
      return self

  def click_about_us(self):
    self._menu.click_about_us()
    return self

  def click_for_authors(self):
    self._menu.click_for_authors()
    return self

  def validate_about_us_links(self):
    # Expectations set up
    expectedLinks = {
      'Journal Information': '%sstatic/information' % self.PROD_URL,
      'Editors-in-Chief': '%sstatic/eic' % self.PROD_URL,
      'Editorial Board': '%sstatic/edboard' % self.PROD_URL,
      'Contact Us': '%sstatic/contact' % self.PROD_URL,
      'Reviewer Guidelines': '%sstatic/reviewerGuidelines' % self.PROD_URL,
      'Guidelines for Comments': '%sstatic/commentGuidelines' % self.PROD_URL,
      'Corrections': '%sstatic/corrections' % self.PROD_URL,
      'Article-Level Metrics': '%sstatic/almInfo' % self.PROD_URL,
      'Help Using This Site': '%sstatic/help' % self.PROD_URL,
      'Media Downloads': '%sstatic/downloads' % self.PROD_URL,
      'Open-Access License': '%sstatic/license' % self.PROD_URL,
      'Frequently Asked Questions': '%sstatic/faq' % self.PROD_URL
    }

    self._menu.validate_about_us_links(expectedLinks)

  def validate_for_authors_links(self):
    # Expectations set up
    expectedLinks = {
      'Funded by RCUK': '%sstatic/rcuk' % self.PROD_URL,
      'Editorial and Publishing Policies': '%sstatic/policies' % self.PROD_URL,
      'Author Guidelines': '%sstatic/guidelines' % self.PROD_URL,
      'Figure and Table Guidelines': '%sstatic/figureGuidelines' % self.PROD_URL,
      'Supporting Information Guidelines': '%sstatic/supportingInformation' % self.PROD_URL,
      'Submit Your Paper': '%sstatic/checklist' % self.PROD_URL,
      'Submit to Other PLOS Journals': 'http://www.plos.org/journals/index.php'
    }

    self._menu.validate_for_authors_links(expectedLinks)
