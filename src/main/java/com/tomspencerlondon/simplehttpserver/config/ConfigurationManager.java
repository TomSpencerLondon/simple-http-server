package com.tomspencerlondon.simplehttpserver.config;

public class ConfigurationManager {

  private static ConfigurationManager myConfigurationManager;
  private static Configuration myCurrentConfiguration;

  private ConfigurationManager() {
  }

  public static ConfigurationManager getInstance() {
    if (myConfigurationManager == null) {
      myConfigurationManager = new ConfigurationManager();
    }

    return myConfigurationManager;
  }

  /**
   *
   * Used to load a configuration file by the path provided
   */
  public void loadConfigurationFile(String filePath) {
  }

  /**
   * returns the current loaded configuration
   */
  public void getCurrentConfiguration() {

  }
}
