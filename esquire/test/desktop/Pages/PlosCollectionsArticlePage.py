#!/usr/bin/env python2

__author__ = 'jgray@plos.org'

from ArticlePage import ArticlePage
import random

dois = [ '/DesktopPlosCollections/article?id=10.1371/journal.pbio.1001569' ]

class PlosCollectionsArticlePage(ArticlePage):
  """
  Model the PLoS Collections Article page.
  """
  PROD_URL = 'http://journals.plos.org/ploscollections/article?id=10.1371/journal.pbio.1001569'

  def __init__(self, driver):
    super(PlosCollectionsArticlePage, self).__init__(driver, random.choice(dois))


    # POM - Instance members

    # Locators - Instance members

  # POM Actions
