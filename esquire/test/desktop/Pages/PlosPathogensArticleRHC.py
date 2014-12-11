#!/usr/bin/env python2

__author__ = 'jgray@plos.org'

from ArticleRHC import ArticleRHC
import random

dois = [ '/DesktopPlosPathogens/article?id=10.1371/journal.ppat.0020025',
         '/DesktopPlosPathogens/article?id=10.1371/journal.ppat.0040045',
         '/DesktopPlosPathogens/article?id=10.1371/journal.ppat.1000105',
         '/DesktopPlosPathogens/article?id=10.1371/journal.ppat.1000166',
         '/DesktopPlosPathogens/article?id=10.1371/journal.ppat.1001009',
         '/DesktopPlosPathogens/article?id=10.1371/journal.ppat.1002247',
         '/DesktopPlosPathogens/article?id=10.1371/journal.ppat.1002735',
         '/DesktopPlosPathogens/article?id=10.1371/journal.ppat.1002769',
         '/DesktopPlosPathogens/article?id=10.1371/journal.ppat.1003133',
         '/DesktopPlosPathogens/article?id=10.1371/journal.ppat.1004200',
         '/DesktopPlosPathogens/article?id=10.1371/journal.ppat.1004377',
         '/DesktopPlosPathogens/article?id=10.1371/journal.ppat.1004389',
         '/DesktopPlosPathogens/article?id=10.1371/journal.ppat.1004411'
]


class PlosPathogensArticleRHC(ArticleRHC):
  """
  Model the PLoS Pathogens Article right hand column.
  """

  PROD_URL = 'http://journals.plos.org/plospathogens/article?id=10.1371/journal.ppat.1004200'

  def __init__(self, driver):
    super(PlosPathogensArticleRHC, self).__init__(driver, random.choice(dois))


    # POM - Instance members

    # Locators - Instance members

    self._twitter_hashtag = '#PLOSPathogens'

  # POM Actions

