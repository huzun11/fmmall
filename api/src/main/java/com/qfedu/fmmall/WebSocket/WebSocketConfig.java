package com.qfedu.fmmall.WebSocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {

    @Bean//使spring容器里有这么个东西
    public ServerEndpointExporter getServerEndpointExporter(){

        return new ServerEndpointExporter();
    }
}
