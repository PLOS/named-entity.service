#!/usr/bin/env python2

__author__ = 'jgray@plos.org'

from ArticleRHC import ArticleRHC
import random

dois = [ '/DesktopPlosCompBiol/article?id=10.1371/journal.pcbi.0020120',
         '/DesktopPlosCompBiol/article?id=10.1371/journal.pcbi.0030134',
         '/DesktopPlosCompBiol/article?id=10.1371/journal.pcbi.0030158',
         '/DesktopPlosCompBiol/article?id=10.1371/journal.pcbi.1000112',
         '/DesktopPlosCompBiol/article?id=10.1371/journal.pcbi.1000589',
         '/DesktopPlosCompBiol/article?id=10.1371/journal.pcbi.1000974',
         '/DesktopPlosCompBiol/article?id=10.1371/journal.pcbi.1001051',
         '/DesktopPlosCompBiol/article?id=10.1371/journal.pcbi.1001083',
         '/DesktopPlosCompBiol/article?id=10.1371/journal.pcbi.1002484',
         '/DesktopPlosCompBiol/article?id=10.1371/journal.pcbi.1003842',
         '/DesktopPlosCompBiol/article?id=10.1371/journal.pcbi.1003849'
]


class PlosCompBiolArticleRHC(ArticleRHC):
  """
  Model the PLoS Computational Biology article Right hand Column.
  """

  PROD_URL = 'http://journals.plos.org/ploscompbiol/article?id=10.1371/journal.pcbi.0020120'

  def __init__(self, driver):
    super(PlosCompBiolArticleRHC, self).__init__(driver, random.choice(dois))


    # POM - Instance members

    # Locators - Instance members

    self._twitter_hashtag = '#PLOSCompBio'

  # POM Actions
