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
