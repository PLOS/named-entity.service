#!/usr/bin/env python2

__author__ = 'jgray@plos.org'

from HomePage import HomePage
from Menu import Menu


class PlosBiologyHomePage(HomePage):

  """
  Model the PLoS Biology Journal page.
  """

  PROD_URL = 'http://www.plosbiology.org/'

  def __init__(self, driver):
    super(PlosBiologyHomePage, self).__init__(driver, '/DesktopPlosBiology/')

    # POM - Instance members
    self._menu = Menu(driver)

    # Locators - Instance members

  # POM Actions

  def validate_about_us_links(self):
    # Expectations set up
    expected_links = {
      'Journal Information': '%sstatic/information' % self.PROD_URL,
      'Editorial Team': 'http://www.plos.org/about/people/staff/#publications-and-products',
      'Editorial Board': '%sstatic/edboard' % self.PROD_URL,
      'Reviewer Guidelines': '%sstatic/reviewerGuidelines' % self.PROD_URL,
      'Academic Editor Guidelines': '%sstatic/aeGuidelines' % self.PROD_URL,
      'Article-Level Metrics': '%sstatic/almInfo' % self.PROD_URL,
      'Open-Access License': '%sstatic/license' % self.PROD_URL,
      'PLOS in Print': 'http://www.plos.org/publish/order-reprints/',
      'Guidelines for Comments': '%sstatic/commentGuidelines' % self.PROD_URL,
      'Corrections': '%sstatic/corrections' % self.PROD_URL,
      'Help Using This Site': '%sstatic/help' % self.PROD_URL,
      'Contact Us': '%sstatic/contact' % self.PROD_URL,
      'Media Downloads': '%sstatic/downloads' % self.PROD_URL
    }

    self._menu.validate_about_us_links(expected_links)

  def validate_browse_links(self):
    # Expectations set up
    expected_links = {
      'Current Issue': '%sarticle/browse/issue' % self.PROD_URL,
      'Journal Archive': '%sarticle/browse/volume' % self.PROD_URL,
      'Collections': 'http://www.ploscollections.org/static/pbioCollections'
    }

    self._menu.validate_browse_links(expected_links)

  def validate_for_authors_links(self):
    # Expectations set up
    expected_links = {
      'Publish with PLOS': 'http://www.plos.org/journals',
      'Funded by RCUK': '%sstatic/rcuk' % self.PROD_URL,
      'Editorial and Publishing Policies': '%sstatic/policies' % self.PROD_URL,
      'Author Guidelines': '%sstatic/guidelines' % self.PROD_URL,
      'Figure and Table Guidelines': '%sstatic/figureGuidelines' % self.PROD_URL,
      'Supporting Information Guidelines': '%sstatic/supportingInformation' % self.PROD_URL,
      'Submit Your Paper': '%sstatic/checklist' % self.PROD_URL
    }

    self._menu.validate_for_authors_links(expected_links)
