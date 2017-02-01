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
