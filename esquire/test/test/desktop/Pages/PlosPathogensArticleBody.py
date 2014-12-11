#!/usr/bin/env python2

__author__ = 'jgray@plos.org'

from ArticleBody import ArticleBody
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


class PlosPathogensArticleBody(ArticleBody):
  """
  Model the PLoS Pathogens Article page, article tab.
  """

  PROD_URL = 'http://journals.plos.org/plospathogens/article?id=10.1371/journal.ppat.1004200'

  def __init__(self, driver):
    super(PlosPathogensArticleBody, self).__init__(driver, random.choice(dois))


    # POM - Instance members

    # Locators - Instance members

  # POM Actions
