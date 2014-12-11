#!/usr/bin/env python2

__author__ = 'jgray@plos.org'

from ArticleRHC import ArticleRHC
import random

dois = [ '/DesktopPlosClinicalTrials/article?id=10.1371/journal.pctr.0020028' ]


class PlosClinicalTrialsArticleRHC(ArticleRHC):
  """
  Model the PLoS Clinical Trials Article Right Hand Column.
  """
  PROD_URL = 'http://journals.plos.org/plosclinicaltrials/article?id=10.1371/journal.pctr.0020028'

  def __init__(self, driver):
    super(PlosClinicalTrialsArticleRHC, self).__init__(driver, random.choice(dois))

    # POM - Instance members

    # Locators - Instance members

    self._twitter_hashtag = '#PLOS'

  # POM Actions
