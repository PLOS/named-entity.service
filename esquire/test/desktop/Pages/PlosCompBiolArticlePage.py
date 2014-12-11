#!/usr/bin/env python2

__author__ = 'jgray@plos.org'

from ArticlePage import ArticlePage
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


class PlosCompBiolArticlePage(ArticlePage):
  """
  Model the PLoS Computational Biology article page.
  """

  PROD_URL = 'http://journals.plos.org/ploscompbiol/article?id=10.1371/journal.pcbi.0020120'

  def __init__(self, driver):
    super(PlosCompBiolArticlePage, self).__init__(driver, random.choice(dois))


    # POM - Instance members

    # Locators - Instance members

  # POM Actions
