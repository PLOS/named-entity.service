#!/usr/bin/env python2

__author__ = 'jgray@plos.org'

from ArticlePage import ArticlePage
import random

dois = [ '/DesktopPlosNtds/article?id=10.1371/journal.pntd.0000149',
         '/DesktopPlosNtds/article?id=10.1371/journal.pntd.0001041',
         '/DesktopPlosNtds/article?id=10.1371/journal.pntd.0001446',
         '/DesktopPlosNtds/article?id=10.1371/journal.pntd.0001969',
         '/DesktopPlosNtds/article?id=10.1371/journal.pntd.0002570',
         '/DesktopPlosNtds/article?id=10.1371/journal.pntd.0002958',
         '/DesktopPlosNtds/article?id=10.1371/journal.pntd.0003188',
         '/DesktopPlosNtds/article?id=10.1371/journal.pntd.0003205'
]


class PlosNeglectedArticlePage(ArticlePage):
  """
  Model the PLoS Neglected Article page.
  """

  PROD_URL = 'http://journals.plos.org/plosntds/article?id=10.1371/journal.pntd.0002958'

  def __init__(self, driver):
    super(PlosNeglectedArticlePage, self).__init__(driver, random.choice(dois))

    # POM - Instance members

    # Locators - Instance members


  # POM Actions
