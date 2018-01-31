package com.google.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Controller
public class WebSocketController {
    /**
     * The webSocket protocol provides a standardized way to establish a full-duplex,two way communication channel between client and the server
     * over a single TCP connection,typical webSocket request headers are:
     *  GET /spring-websocket-portfolio/portfolio HTTP/1.1
        Host: localhost:8080
        Upgrade: websocket
        Connection: Upgrade
        Sec-WebSocket-Key: Uc9l9TMkWGbHFD2qnFHltg==
        Sec-WebSocket-Protocol: v10.stomp, v11.stomp
        Sec-WebSocket-Version: 13
        Origin: http://localhost:8080

     * instead of the usual 200 response code,a server with WebSocket support returns:
     *      HTTP/1.1 101 Switching Protocols
            Upgrade: websocket
            Connection: Upgrade
            Sec-WebSocket-Accept: 1qVdfYHU9hPOl4JYYNXF623Gzn0=
            Sec-WebSocket-Protocol: v10.stomp

     *
     * After a successful handshake the TCP socket underlying the HTTP upgrade request remains open for both client and server to continue to send and receive messages.
     *
     * Note that if a WebSocket server is running behind a web server (e.g. nginx) you will likely need to configure it
     * to pass WebSocket upgrade requests on to the WebSocket server.
     *
     * In HTTP and REST, an application is modeled as many URLs. To interact with the application clients access those URLs,
     * request-response style. Servers route requests to the appropriate handler based on the HTTP URL, method, and headers.
     *
     * By contrast in WebSockets there is usually just one URL for the initial connect and subsequently all application
     * messages flow on that same TCP connection. This points to an entirely different asynchronous, event-driven, messaging architecture.
     *
     * WebSockets can make a web page dynamic and interactive. However in many cases a combination of Ajax and HTTP
     * streaming and/or long polling could provide a simple and effective solution.
     */
}
