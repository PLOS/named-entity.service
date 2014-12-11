#!/usr/bin/env python2

__author__ = 'jgray@plos.org'

from HomePage import HomePage
from MenuManuscript import MenuManuscript


class PlosMedicineHomePage(HomePage):

  """
  Model the PLoS Medicine Journal page.
  """

  PROD_URL = 'http://www.plosmedicine.org/'

  def __init__(self, driver):
    super(PlosMedicineHomePage, self).__init__(driver, '/DesktopPlosMedicine/')

    # POM - Instance members
    self._menu = MenuManuscript(driver)

    # POM - Locators section

  # POM Actions

  def validate_about_us_links(self):
    # Expectations set up
    expected_links = {
      'Journal Information': '%sstatic/information' % self.PROD_URL,
      'Editorial Staff': 'http://www.plos.org/about/people/staff/#plos-medicine',
      'Editorial Board': '%sstatic/edboard' % self.PROD_URL,
      'Contact Us': '%sstatic/contact' % self.PROD_URL,
      'Reviewer Guidelines': '%sstatic/reviewerGuidelines' % self.PROD_URL,
      'Guidelines for Comments': '%sstatic/commentGuidelines' % self.PROD_URL,
      'Corrections': '%sstatic/corrections' % self.PROD_URL,
      'Article-Level Metrics': '%sstatic/almInfo' % self.PROD_URL,
      'Frequently Asked Questions': '%sstatic/faq' % self.PROD_URL,
      'Help Using This Site': '%sstatic/help' % self.PROD_URL,
      'PLOS in Print': 'http://www.plos.org/publish/order-reprints/',
      'Media Downloads': '%sstatic/downloads' % self.PROD_URL,
      'Open-Access License': '%sstatic/license' % self.PROD_URL,
      'Student Blog': '%sstatic/studentforum' % self.PROD_URL,
      'Wyeth Ghostwriting Archive': '%sstatic/ghostwriting' % self.PROD_URL
    }

    self._menu.validate_about_us_links(expected_links)

  def validate_browse_links(self):
    # Expectations set up
    expected_links = {
      'Current Issue': '%sarticle/browse/issue' % self.PROD_URL,
      'Journal Archive': '%sarticle/browse/volume' % self.PROD_URL,
      'Collections': 'http://www.ploscollections.org/static/pmedCollections'
    }

    self._menu.validate_browse_links(expected_links)

  def validate_for_authors_links(self):
    # Expectations set up
    expected_links = {
      'Publish with PLOS': 'http://www.plos.org/journals',
      'Why Publish With Us?': '%sstatic/benefits' % self.PROD_URL,
      'Editorial and Publishing Policies': '%sstatic/policies' % self.PROD_URL,
      'Author Guidelines': '%sstatic/guidelines' % self.PROD_URL,
      'Figure and Table Guidelines': '%sstatic/figureGuidelines' % self.PROD_URL,
      'Supporting Information Guidelines': '%sstatic/supportingInformation' % self.PROD_URL,
      'Submit Your Paper': '%sstatic/checklist' % self.PROD_URL,
    }

    self._menu.validate_for_authors_links(expected_links)

  def validate_submit_your_manuscript_section(self):
    # Expectations set up
    expected_bullets = {
      'Reaching 100,000 readers per month': '',
      'Publishing outstanding research and commentary': '',
      'See the impact of your work': ''
    }

    self._menu.validate_submit_your_manuscript_section(expected_bullets)
