#!/usr/bin/env python2

__author__ = 'jkrzemien@plos.org'

from selenium.webdriver.common.by import By
from selenium.common.exceptions import NoSuchElementException
from Menu import Menu
from ...Base import Config


class MenuManuscript(Menu):

  """
  Model the mobile PLoS site menu (with Submit Your Manuscript section).
  """

  def __init__(self, driver):
    super(MenuManuscript, self).__init__(driver)

    # Locators - Instance members
    self._manuscript_panel = (By.ID, 'submit-manuscript-container')
    self._get_started_link = (By.PARTIAL_LINK_TEXT, 'get started')
    self._submit_your_manuscript_title = (By.XPATH, './h4')
    self._text_bullets = (By.XPATH, ".//li")

  # POM Actions
  def click_get_started_link(self):
    print 'Click Get Started button'
    get_started = self._get(self._manuscript_panel).find_element(*self._get_started_link)
    get_started.click()
    return self

  def _validate_section_title(self):
    print 'Starting validation of Submit Your Manuscript section...'
    print 'Validating title...'
    title = self._get(self._manuscript_panel).find_element(*self._submit_your_manuscript_title)
    assert title.text == 'Submit Your Manuscript'

  def _validate_bullet_contains_link(self, bullet, bulletLink):
    try:
      print 'Checking for links in bullet...'
      self._driver.implicitly_wait(1)
      subLink = bullet.find_element(By.XPATH, './a')
      if subLink:
        print 'Bullet has a link, validating it...',
        assert subLink.get_attribute('href') ==  bulletLink
        print 'HREF OK /',
        self._is_link_valid(subLink)
    except NoSuchElementException:
      pass
    self._driver.implicitly_wait(Config.wait_timeout)

  def _validate_section_bullets(self, expectedBullets):
    print 'Starting validation of bullets...'
    actualBullets = self._get(self._manuscript_panel).find_elements(*self._text_bullets)

    bulletCount1 = len(actualBullets)
    bulletCount2 = len(expectedBullets)
    print "Actual bullet count is %s (Expected %s)" % (bulletCount1, bulletCount2)
    assert bulletCount1 == bulletCount2

    for bullet in actualBullets:
      self._wait_for_element(bullet)
      assert bullet.is_displayed() is True
      bulletText = bullet.text.strip()
      print 'Verifiying bullet "%s":' % bulletText,
      assert bulletText in expectedBullets
      print 'PRESENT'
      expectedLink = expectedBullets[bulletText]
      if expectedLink is not None and expectedLink is not '':
        self._validate_bullet_contains_link(bullet, expectedLink)

  def validate_submit_your_manuscript_section(self, expectedBullets):
    self._validate_section_title()
    self._validate_section_bullets(expectedBullets)

