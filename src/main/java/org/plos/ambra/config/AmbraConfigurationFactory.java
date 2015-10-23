package org.plos.ambra.config;

import org.apache.commons.configuration.Configuration;
import org.ambraproject.configuration.ConfigurationStore;

public class AmbraConfigurationFactory {

  public Configuration getConfiguration() throws Exception {
    ConfigurationStore configurationStore = ConfigurationStore.getInstance();
    configurationStore.loadConfiguration(null);
    return configurationStore.getConfiguration();
  }
}
