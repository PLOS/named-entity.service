#!/usr/bin/env python2

__author__ = 'jkrzemien@plos.org'

from ...Base import Config as Config
from HomePage import HomePage
from MenuManuscript import MenuManuscript


class PlosNeglectedHomePage(HomePage):

  """
  Model the PLoS Neglected Journal page.
  """

  PROD_URL = 'http://www.plosntds.org/'

  def __init__(self, driver):
    super(PlosNeglectedHomePage, self).__init__(driver, '/MobilePlosNtds/')

    # POM - Instance members
    self._menu = MenuManuscript(driver)

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

  def click_get_started_link(self):
    self._menu.click_get_started_link()
    return self

  def validate_about_us_links(self):
    # Expectations set up
    expectedLinks = {
      'Journal Information': '%sstatic/information' % self.PROD_URL,
      'Journal Scope': '%sstatic/scope' % self.PROD_URL,
      'Editorial Board': '%sstatic/edboard' % self.PROD_URL,
      'Editors-in-Chief': '%sstatic/eic' % self.PROD_URL,
      'Open-Access License': '%sstatic/license' % self.PROD_URL,
      'Article-Level Metrics': '%sstatic/almInfo' % self.PROD_URL,
      'Media Downloads': '%sstatic/downloads' % self.PROD_URL,
      'Resources': '%sstatic/resources' % self.PROD_URL,
      'Reviewer Guidelines': '%sstatic/reviewerGuidelines' % self.PROD_URL,
      'Guidelines for Comments': '%sstatic/commentGuidelines' % self.PROD_URL,
      'Corrections': '%sstatic/corrections' % self.PROD_URL,
      'Help Using This Site': '%sstatic/help' % self.PROD_URL,
      'Contact Us': '%sstatic/contact' % self.PROD_URL
    }

    self._menu.validate_about_us_links(expectedLinks)

  def validate_for_authors_links(self):
    # Expectations set up
    expectedLinks = {
      'Why Publish With Us?': '%sstatic/whypublish' % self.PROD_URL,
      'Developing Countries': '%sstatic/developing' % self.PROD_URL,
      'Editorial and Publishing Policies': '%sstatic/policies' % self.PROD_URL,
      'Author Guidelines': '%sstatic/guidelines' % self.PROD_URL,
      'Figure and Table Guidelines': '%sstatic/figureGuidelines' % self.PROD_URL,
      'Supporting Information Guidelines': '%sstatic/supportingInformation' % self.PROD_URL,
      'Submit Your Paper': '%sstatic/checklist' % self.PROD_URL,
      'Call for Papers': '%sstatic/hppcallforpapers' % self.PROD_URL,
      'Submit to Other PLOS Journals': 'http://www.plos.org/journals/index.php'
    }

    self._menu.validate_for_authors_links(expectedLinks)

  def validate_submit_your_manuscript_section(self):
    # Expectations set up
    expectedBullets = {
      'Top open access tropical medicine journal': '',
      'International Editorial Board and community of readers': '',
      'Global Participation Initiative for developing country authors': 'http://www.plos.org/about/viewpoints/global-participation-initiative/'
    }

    self._menu.validate_submit_your_manuscript_section(expectedBullets)