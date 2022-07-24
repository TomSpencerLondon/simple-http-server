package com.tomspencerlondon.simplehttpserver;

import com.tomspencerlondon.simplehttpserver.config.Configuration;
import com.tomspencerlondon.simplehttpserver.config.ConfigurationManager;
import java.io.IOException;

public class HttpServer {

  public static void main(String[] args) throws IOException {
    System.out.println("Hello from HttpServer");

    ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");

    Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

    System.out.println("Using port: " + conf.getPort());
    System.out.println("Using webroot: " + conf.getWebroot());
  }
}
