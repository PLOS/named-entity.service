#!/usr/bin/env python2

__author__ = 'jgray@plos.org'

from ArticleRHC import ArticleRHC
import random

dois = ['/DesktopPlosGenetics/article?id=10.1371/journal.pgen.1000052',
        '/DesktopPlosGenetics/article?id=10.1371/journal.pgen.1002644',
        '/DesktopPlosGenetics/article?id=10.1371/journal.pgen.1002912',
        '/DesktopPlosGenetics/article?id=10.1371/journal.pgen.1003316',
        '/DesktopPlosGenetics/article?id=10.1371/journal.pgen.1003500',
        '/DesktopPlosGenetics/article?id=10.1371/journal.pgen.1004451',
        '/DesktopPlosGenetics/article?id=10.1371/journal.pgen.1004643'
]


class PlosGeneticsArticleRHC(ArticleRHC):
  """
  Model the PLoS Genetics Article right hand column.
  """

  PROD_URL = 'http://journals.plos.org/plosgenetics/article?id=10.1371/journal.pgen.1004451'

  def __init__(self, driver):
    super(PlosGeneticsArticleRHC, self).__init__(driver, random.choice(dois))

    # POM - Instance members

    # Locators - Instance members

    self._twitter_hashtag = '#PLOSGenetics'

  # POM Actions
