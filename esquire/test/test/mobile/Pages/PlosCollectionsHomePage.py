#!/usr/bin/env python2

__author__ = 'jkrzemien@plos.org'

from ...Base import Config as Config
from HomePage import HomePage


class PlosCollectionsHomePage(HomePage):

  """
  Model the PLoS Collections Journal page.
  """

  PROD_URL = 'http://www.ploscollections.org/'

  def __init__(self, driver):
    super(PlosCollectionsHomePage, self).__init__(driver, '/MobilePlosCollections/')

    # Locators - Instance members

  # POM Actions


