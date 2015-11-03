package org.plos.ambra.config;

import org.ambraproject.configuration.ConfigurationStore;
import org.apache.commons.configuration.CombinedConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.tree.OverrideCombiner;

public class AmbraConfigurationFactory {

  public AmbraConfigurationFactory() {
    // important to define doi prefix property BEFORE it's referenced by 
    // the Ambra org.ambraproject.util.URIGenerator class in a static block 
    // (ie, at class load time)
    System.setProperty("SYSTEM_OBJECT_ID_PREFIX", "info:doi/10.1371/");
  }

  public Configuration getConfiguration() throws Exception {
    ConfigurationStore configstore = ConfigurationStore.getInstance();
    configstore.setConfiguration(new CombinedConfiguration(new OverrideCombiner()));
    return configstore.getConfiguration();
  }
}
