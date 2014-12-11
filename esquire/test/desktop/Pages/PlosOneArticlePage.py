#!/usr/bin/env python2

__author__ = 'jgray@plos.org'

from ArticlePage import ArticlePage
import random

dois = [ '/DesktopPlosOne/article?id=10.1371/journal.pone.0000000',
         '/DesktopPlosOne/article?id=10.1371/journal.pone.0002554',
         '/DesktopPlosOne/article?id=10.1371/journal.pone.0005723',
         '/DesktopPlosOne/article?id=10.1371/journal.pone.0008519',
         '/DesktopPlosOne/article?id=10.1371/journal.pone.0008915',
         '/DesktopPlosOne/article?id=10.1371/journal.pone.0010685',
         '/DesktopPlosOne/article?id=10.1371/journal.pone.0016329',
         '/DesktopPlosOne/article?id=10.1371/journal.pone.0016976',
         '/DesktopPlosOne/article?id=10.1371/journal.pone.0026358',
         '/DesktopPlosOne/article?id=10.1371/journal.pone.0027062',
         '/DesktopPlosOne/article?id=10.1371/journal.pone.0028031',
         '/DesktopPlosOne/article?id=10.1371/journal.pone.0036880',
         '/DesktopPlosOne/article?id=10.1371/journal.pone.0040259',
         '/DesktopPlosOne/article?id=10.1371/journal.pone.0040740',
         '/DesktopPlosOne/article?id=10.1371/journal.pone.0042593',
         '/DesktopPlosOne/article?id=10.1371/journal.pone.0046041',
         '/DesktopPlosOne/article?id=10.1371/journal.pone.0047391',
         '/DesktopPlosOne/article?id=10.1371/journal.pone.0050698',
         '/DesktopPlosOne/article?id=10.1371/journal.pone.0052690',
         '/DesktopPlosOne/article?id=10.1371/journal.pone.0055490',
         '/DesktopPlosOne/article?id=10.1371/journal.pone.0057943',
         '/DesktopPlosOne/article?id=10.1371/journal.pone.0058242',
         '/DesktopPlosOne/article?id=10.1371/journal.pone.0066742',
         '/DesktopPlosOne/article?id=10.1371/journal.pone.0067179',
         '/DesktopPlosOne/article?id=10.1371/journal.pone.0067227',
         '/DesktopPlosOne/article?id=10.1371/journal.pone.0067380',
         '/DesktopPlosOne/article?id=10.1371/journal.pone.0068090',
         '/DesktopPlosOne/article?id=10.1371/journal.pone.0069640',
         '/DesktopPlosOne/article?id=10.1371/journal.pone.0081648',
         '/DesktopPlosOne/article?id=10.1371/journal.pone.0087236',
         '/DesktopPlosOne/article?id=10.1371/journal.pone.0100977',
         '/DesktopPlosOne/article?id=10.1371/journal.pone.0105948',
         '/DesktopPlosOne/article?id=10.1371/journal.pone.0108198'
]


class PlosOneArticlePage(ArticlePage):
  """
  Model the PLoS One Article page.
  """
  PROD_URL = 'http://journals.plos.org/plosone/article?id=10.1371/journal.pone.0000000'

  def __init__(self, driver):
    super(PlosOneArticlePage, self).__init__(driver, random.choice(dois))

    # POM - Instance members

    # Locators - Instance members

  # POM Actions
