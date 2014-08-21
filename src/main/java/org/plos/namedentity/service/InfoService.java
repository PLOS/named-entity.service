/*
 * Copyright (c) 2006-2014 by Public Library of Science
 * http://plos.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.plos.namedentity.service;

import org.plos.namedentity.api.ConfigInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class InfoService {

  private static final Logger log = LoggerFactory.getLogger(InfoService.class);

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
    config.startime = startTime;
    return config;
  }

}
