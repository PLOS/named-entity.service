#!/usr/bin/env python2

__author__ = 'jgray@plos.org'

from ArticleBody import ArticleBody
import random

dois = [ '/DesktopPlosCollections/article?id=10.1371/journal.pbio.1001569' ]


class PlosCollectionsArticleBody(ArticleBody):
  """
  Model the PLoS Collections Article page, article tab.
  """
  PROD_URL = 'http://journals.plos.org/ploscollections/article?id=10.1371/journal.pbio.1001569'

  def __init__(self, driver):
    super(PlosCollectionsArticleBody, self).__init__(driver, random.choice(dois))


    # POM - Instance members

    # Locators - Instance members

  # POM Actions
