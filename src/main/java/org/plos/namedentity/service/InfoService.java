/*
 * Copyright (c) 2017 Public Library of Science
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */
package org.plos.namedentity.service;

import org.plos.namedentity.api.ConfigInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class InfoService {

  private static final Logger log = LoggerFactory.getLogger(InfoService.class);

  @Inject protected NamedEntityService namedEntityService;

  private String projectVersion = "unknown";

  private Date startTime;

  @PostConstruct
  public void init() {

    startTime = new Date();

    try (InputStream is = getClass().getResourceAsStream("/version.properties")) {
      Properties properties = new Properties();
      properties.load(is);
      projectVersion = properties.get("version") + " (" + properties.get("buildDate") + ")";
    } catch (Exception e) {
      log.error("Error fetching project version", e);
    }
  }

  public ConfigInfo getConfig() {

    ConfigInfo config = new ConfigInfo();
    config.version = projectVersion;
    config.starTime = startTime;
    config.globalTypeCount = namedEntityService.countGlobalTypes();
    config.consumerCount = namedEntityService.countConsumers();

    return config;
  }

}
