package org.plos.namedentity.suite;
 
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import org.plos.namedentity.persist.NamedEntityDBServiceTest;
import org.plos.namedentity.rest.NamedEntityResourceTest;
import org.plos.namedentity.service.NamedEntityServiceHighApiTest;
import org.plos.namedentity.service.NamedEntityServiceTest;

@RunWith(Suite.class)
@SuiteClasses({
    NamedEntityDBServiceTest.class, 
    NamedEntityServiceTest.class, 
    NamedEntityServiceHighApiTest.class,
    NamedEntityResourceTest.class
})
public class TestSuite {
}
