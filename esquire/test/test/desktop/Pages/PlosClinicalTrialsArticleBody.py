#!/usr/bin/env python2

__author__ = 'jgray@plos.org'

from ArticleBody import ArticleBody
import random

dois = [ '/DesktopPlosClinicalTrials/article?id=10.1371/journal.pctr.0020028' ]


class PlosClinicalTrialsArticleBody(ArticleBody):

  """
  Model the PLoS Clinical Trials Article page, article tab.
  """
  PROD_URL = 'http://journals.plos.org/plosclinicaltrials/article?id=10.1371/journal.pctr.0020028'

  def __init__(self, driver):
    super(PlosClinicalTrialsArticleBody, self).__init__(driver, random.choice(dois))

    # POM - Instance members

    # Locators - Instance members

  # POM Actions
