package org.plos.ambra.config;

import org.ambraproject.configuration.ConfigurationStore;
import org.apache.commons.configuration.CombinedConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.tree.OverrideCombiner;

public class AmbraConfigurationFactory {

  public Configuration getConfiguration() throws Exception {
    ConfigurationStore configstore = ConfigurationStore.getInstance();
    configstore.setConfiguration(new CombinedConfiguration(new OverrideCombiner()));
    return configstore.getConfiguration();
  }
}
