package com.tomspencerlondon.simplehttpserver;

import com.tomspencerlondon.simplehttpserver.config.Configuration;
import com.tomspencerlondon.simplehttpserver.config.ConfigurationManager;
import com.tomspencerlondon.simplehttpserver.core.ServerListenerThread;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpServer {

  private final static Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);

  public static void main(String[] args) throws IOException {
    LOGGER.info("Hello from HttpServer");

    ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");

    Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

    LOGGER.info("Using port: " + conf.getPort());
    LOGGER.info("Using webroot: " + conf.getWebroot());

    try {
      ServerListenerThread serverListenerThread = new ServerListenerThread(conf.getPort(), conf.getWebroot());
      serverListenerThread.start();
    } catch (IOException e) {
      e.printStackTrace();
      // TODO Handle later
    }

  }
}
