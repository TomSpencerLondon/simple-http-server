package com.tomspencerlondon.simplehttpserver;

import com.tomspencerlondon.simplehttpserver.config.Configuration;
import com.tomspencerlondon.simplehttpserver.config.ConfigurationManager;
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
      ServerSocket serverSocket = new ServerSocket(conf.getPort());
      Socket socket = serverSocket.accept();

      InputStream inputStream = socket.getInputStream();
      OutputStream outputStream = socket.getOutputStream();

      String html = "<html><head><title>Simple Java HTTP Server</title></head><body><h1>This page was served using my simple Java Http Server</h1></body></html>";

      final String CRLF = "\n\r"; // 13, 10

      String response = "HTTP/1.1 200 OK" + CRLF +  // status line : HTTP_VERSION RESPONSE_CODE RESPONSE_MESSAGE
          "Content-Length: " + html.getBytes().length + CRLF + // HEADER
          CRLF + html + CRLF + CRLF;

      outputStream.write(response.getBytes());

      inputStream.close();
      outputStream.close();
      socket.close();
      serverSocket.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
