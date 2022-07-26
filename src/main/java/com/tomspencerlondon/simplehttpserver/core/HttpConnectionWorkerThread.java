package com.tomspencerlondon.simplehttpserver.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpConnectionWorkerThread extends Thread {

  private final static Logger LOGGER = LoggerFactory.getLogger(HttpConnectionWorkerThread.class);

  private final Socket socket;

  public HttpConnectionWorkerThread(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    InputStream inputStream = null;
    OutputStream outputStream = null;
    try {
      inputStream = socket.getInputStream();
      outputStream = socket.getOutputStream();

      int _byte;

      while ((_byte = inputStream.read()) >= 0) {
        System.out.print((char) _byte);
      }

      String html = "<html><head><title>Simple Java HTTP Server</title></head><body><h1>This page was served using my simple Java Http Server</h1></body></html>";

      final String CRLF = "\n\r"; // 13, 10

      String response = "HTTP/1.1 200 OK" + CRLF +  // status line : HTTP_VERSION RESPONSE_CODE RESPONSE_MESSAGE
          "Content-Length: " + html.getBytes().length + CRLF + // HEADER
          CRLF + html + CRLF + CRLF;

      outputStream.write(response.getBytes());

      LOGGER.info("Processing finished");
    } catch (IOException e) {
      LOGGER.error("Problem with communication :" + e);
    } finally {
      if (inputStream != null) {

        try {
          inputStream.close();
        } catch (IOException e) {}
      }

      if (outputStream != null) {

        try {
          outputStream.close();
        } catch (IOException e) {}
      }
      if (socket != null) {
        try {
          socket.close();
        } catch (IOException e) {}
      }
    }
  }
}
