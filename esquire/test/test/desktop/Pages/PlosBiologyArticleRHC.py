#!/usr/bin/env python2

__author__ = 'jgray@plos.org'

from ArticleRHC import ArticleRHC
import random

dois = [ '/DesktopPlosBiology/article?id=10.1371/journal.pbio.0030408',
         '/DesktopPlosBiology/article?id=10.1371/journal.pbio.0040088',
         '/DesktopPlosBiology/article?id=10.1371/journal.pbio.1001199',
         '/DesktopPlosBiology/article?id=10.1371/journal.pbio.1001291',
         '/DesktopPlosBiology/article?id=10.1371/journal.pbio.1001315',
         '/DesktopPlosBiology/article?id=10.1371/journal.pbio.1001569'
]


class PlosBiologyArticleRHC(ArticleRHC):
  """
  Model the PLoS Biology Article Right Hand Column.
  """
  PROD_URL = 'http://journals.plos.org/plosbiology/article?id=10.1371/journal.pbio.1001569'

  def __init__(self, driver):
    super(PlosBiologyArticleRHC, self).__init__(driver, random.choice(dois))

    # POM - Instance members

    # Locators - Instance members

    self._twitter_hashtag = '#PLOSBiology'

  # POM Actions
