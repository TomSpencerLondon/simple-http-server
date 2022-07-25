package com.tomspencerlondon.simplehttpserver;

import com.tomspencerlondon.simplehttpserver.config.Configuration;
import com.tomspencerlondon.simplehttpserver.config.ConfigurationManager;
import com.tomspencerlondon.simplehttpserver.core.ServerListenerThread;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

  public static void main(String[] args) throws IOException {
    System.out.println("Hello from HttpServer");

    ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");

    Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

    System.out.println("Using port: " + conf.getPort());
    System.out.println("Using webroot: " + conf.getWebroot());

    try {
      ServerListenerThread serverListenerThread = new ServerListenerThread(conf.getPort(), conf.getWebroot());
      serverListenerThread.start();
    } catch (IOException e) {
      e.printStackTrace();
      // TODO Handle later
    }

  }
}
