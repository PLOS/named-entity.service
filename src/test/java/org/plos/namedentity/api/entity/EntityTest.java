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
package org.plos.namedentity.api.entity;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EntityTest {

  @Test
  public void testUrlEqualsAndHashCode() throws Exception {
  
    Url url1 = new Url();
    assertFalse( url1.equals(null) ); 

    Url url2 = new Url() ; verifyTrue(url1,url2);

    url1.setUrl("http://foo")  ; verifyFalse(url1,url2); 
    url2.setUrl(url1.getUrl()) ; verifyTrue(url1,url2);

    url1.id = 1 ; url1.nedid = 100 ; verifyTrue(url1,url2);

    url2.id = url1.id ; url2.nedid = url1.nedid ; verifyTrue(url1,url2);

    url1.source = "Ambra" ; url1.sourcetypeid = 1 ; verifyFalse(url1,url2);

    url2.source = url1.source ; url2.sourcetypeid = url1.sourcetypeid;
    verifyTrue(url1,url2);

    url1.created = now()      ; verifyTrue(url1,url2);
    url1.lastmodified = now() ; verifyTrue(url1,url2);

    url2.created = now()      ; verifyTrue(url1,url2); 
    url2.lastmodified = now() ; verifyTrue(url1,url2); 
  }

  private void verifyTrue(Entity e1, Entity e2) {
    assertEquals(e1, e2);
    assertEquals(e1.hashCode(), e2.hashCode());
  }

  private void verifyFalse(Entity e1, Entity e2) {
    assertFalse(e1.equals(e2));
    assertTrue(e1.hashCode() != e2.hashCode());
  }

  private Timestamp now() {
    return new Timestamp(Calendar.getInstance().getTime().getTime());
  }
}
