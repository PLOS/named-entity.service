#!/usr/bin/env python2

__author__ = 'jgray@plos.org'

from selenium.webdriver.common.by import By
from selenium.common.exceptions import NoSuchElementException
from Menu import Menu
from ...Base import Config


class MenuManuscript(Menu):

  """
  Model the desktop PLos site menu.
  """

  def __init__(self, driver):
    super(MenuManuscript, self).__init__(driver)

    # Locators - Instance members
    self._manuscript_panel = (By.ID, "dropdown-callout-submit")
    self._get_started_link = (By.CSS_SELECTOR, "a.btn")
    self._submit_your_manuscript_title = (By.XPATH, "./h3")
    self._text_bullets = (By.XPATH, ".//li")
    self._sub_link_in_bullet = (By.XPATH, './a')

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

  def _validate_bullet_contains_link(self, bullet, bullet_link):
    try:
      print 'Checking for links in bullet...'
      self._driver.implicitly_wait(1)
      sub_link = bullet.find_element(By.XPATH, './a')
      if sub_link:
        print 'Bullet has a link, validating it...',
        assert sub_link.get_attribute('href') == bullet_link
        print 'HREF OK /',
        self._is_link_valid(sub_link)
    except NoSuchElementException:
      pass
    self._driver.implicitly_wait(Config.wait_timeout)

  def _validate_section_bullets(self, expected_bullets):
    print 'Starting validation of bullets...'
    actual_bullets = self._get(self._manuscript_panel).find_elements(*self._text_bullets)

    bullet_count1 = len(actual_bullets)
    bullet_count2 = len(expected_bullets)
    print "Actual bullet count is %s (Expected %s)" % (bullet_count1, bullet_count2)
    assert bullet_count1 == bullet_count2

    for bullet in actual_bullets:
      self._wait_for_element(bullet)
      assert bullet.is_displayed() is True
      bullet_text = bullet.text.strip()
      print 'Verifying bullet "%s":' % bullet_text,
      assert bullet_text in expected_bullets
      print 'PRESENT'
      expected_link = expected_bullets[bullet_text]
      if expected_link is not None and expected_link is not '':
        self._validate_bullet_contains_link(bullet, expected_link)

  def validate_submit_your_manuscript_section(self, expected_bullets):
    self._validate_section_title()
    self._validate_section_bullets(expected_bullets)
