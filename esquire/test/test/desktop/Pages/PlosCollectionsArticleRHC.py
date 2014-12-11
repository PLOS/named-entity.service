#!/usr/bin/env python2

__author__ = 'jgray@plos.org'

from ArticleRHC import ArticleRHC
import random

dois = [ '/DesktopPlosCollections/article?id=10.1371/journal.pbio.1001569' ]

class PlosCollectionsArticleRHC(ArticleRHC):

  """
  Model the PLoS Collections Article Right Hand Column.
  """
  PROD_URL = 'http://journals.plos.org/ploscollections/article?id=10.1371/journal.pbio.1001569'

  def __init__(self, driver):
    super(PlosCollectionsArticleRHC, self).__init__(driver, random.choice(dois))


    # POM - Instance members

    # Locators - Instance members

    self._twitter_hashtag = '#PLOS'

  # POM Actions
