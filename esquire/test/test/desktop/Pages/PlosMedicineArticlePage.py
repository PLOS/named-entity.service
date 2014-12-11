#!/usr/bin/env python2

__author__ = 'jgray@plos.org'

from ArticlePage import ArticlePage
import random

dois = [ '/DesktopPlosMedicine/article?id=10.1371/journal.pmed.0020007',
         '/DesktopPlosMedicine/article?id=10.1371/journal.pmed.0020124',
         '/DesktopPlosMedicine/article?id=10.1371/journal.pmed.0020171',
         '/DesktopPlosMedicine/article?id=10.1371/journal.pmed.0020402',
         '/DesktopPlosMedicine/article?id=10.1371/journal.pmed.0030132',
         '/DesktopPlosMedicine/article?id=10.1371/journal.pmed.0030205',
         '/DesktopPlosMedicine/article?id=10.1371/journal.pmed.0030445',
         '/DesktopPlosMedicine/article?id=10.1371/journal.pmed.0030520',
         '/DesktopPlosMedicine/article?id=10.1371/journal.pmed.0040303',
         '/DesktopPlosMedicine/article?id=10.1371/journal.pmed.1000097',
         '/DesktopPlosMedicine/article?id=10.1371/journal.pmed.1000431',
         '/DesktopPlosMedicine/article?id=10.1371/journal.pmed.1001080',
         '/DesktopPlosMedicine/article?id=10.1371/journal.pmed.1001188',
         '/DesktopPlosMedicine/article?id=10.1371/journal.pmed.1001202',
         '/DesktopPlosMedicine/article?id=10.1371/journal.pmed.1001200',
         '/DesktopPlosMedicine/article?id=10.1371/journal.pmed.1001210',
         '/DesktopPlosMedicine/article?id=10.1371/journal.pmed.1001300',
         '/DesktopPlosMedicine/article?id=10.1371/journal.pmed.1001418',
         '/DesktopPlosMedicine/article?id=10.1371/journal.pmed.1001473',
         '/DesktopPlosMedicine/article?id=10.1371/journal.pmed.1001518',
         '/DesktopPlosMedicine/article?id=10.1371/journal.pmed.1001644',
         '/DesktopPlosMedicine/article?id=10.1371/journal.pmed.1001743',
         '/DesktopPlosMedicine/article?id=10.1371/journal.pmed.1000431'
]


class PlosMedicineArticlePage(ArticlePage):
  """
  Model the PLoS Medicine Article page.
  """

  PROD_URL = 'http://journals.plos.org/plosmedicine/article?id=10.1371/journal.pmed.1001644'

  def __init__(self, driver):
    super(PlosMedicineArticlePage, self).__init__(driver, random.choice(dois))

    # POM - Instance members

    # POM - Locators section

  # POM Actions
