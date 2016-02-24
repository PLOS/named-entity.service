/*
 * Copyright (c) 2006-2015 by Public Library of Science
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
package org.plos.namedentity.api.adapter;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlRootElement
public class Container {

  private Map<String, String> map;

  public Container() {
    map = new HashMap<String, String>();
  }

  /*
   * if map contained { "valid":true }, adapter will return
   *
   *    { "map":{ "root":{ "valid":"true" }}}
   *
   * TODO: research eliminating nested maps with @XmlPath
   *       for example, return { "valid:"true" }
   */
  @XmlJavaTypeAdapter(MapAdapter.class)
  public Map<String,String> getMap() {
    return map;
  }

  @XmlJavaTypeAdapter(MapAdapter.class)
  public void setMap(Map<String,String> map) {
    this.map = map;
  }
}
