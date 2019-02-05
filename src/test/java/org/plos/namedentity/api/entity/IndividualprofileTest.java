/*
 * Copyright (c) 2014-2019 Public Library of Science
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
import org.plos.namedentity.api.NedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class IndividualprofileTest {

  @Test
  public void testDisplaynameValidation() {

    Entity[] good = {
      fromDisplayname("a"),
      fromDisplayname("10bogus郑超Gebækaaaمن"),
      fromDisplayname("a.b_c(d"),
    };

    Entity[] bad = {
      fromDisplayname("test$"),
      fromDisplayname("test&"),
      fromDisplayname("test+"),
      fromDisplayname("test,"),
      fromDisplayname("test;"),
      fromDisplayname("test:"),
      fromDisplayname("test="),
      fromDisplayname("test?"),
      fromDisplayname("test#"),
      fromDisplayname("test|"),
      fromDisplayname("test/"),
      fromDisplayname("middle space"),
      fromDisplayname("endspace "),
      fromDisplayname("test^"),
      fromDisplayname("test~"),
      fromDisplayname("test%"),
      fromDisplayname("test>"),
      fromDisplayname("test<"),
      fromDisplayname("test{"),
      fromDisplayname("test}"),
      fromDisplayname("test\\"),
      fromDisplayname("test["),
      fromDisplayname("test]"),
    };

    for (Entity e : bad) {
      try {
        e.validate();
        fail();
      } catch (NedException expected) {
        assertEquals(expected.getErrorType(), NedException.ErrorType.DisplayNameError);
      }
    }

    for (Entity e : good) {
      e.validate();
    }

  }

  private static Individualprofile fromDisplayname(String displayName) {
    Individualprofile p = new Individualprofile();
    p.setFirstname("first");
    p.setLastname("last");
    p.setDisplayname(displayName);
    return p;
  }
}
