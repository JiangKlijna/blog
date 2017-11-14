package com.jiangKlijna.web.websocket

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor
import javax.annotation.Resource

/**
 * Created by jiangKlijna on 9/20/2017.
 */
@Configuration
@EnableWebSocket
open class WebSocketConfig : WebSocketConfigurer {

    @Resource(name = "ChatWebSocketHandler")
    var handler: ChatWebSocketHandler? = null

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        println("registerWebSocketHandlers" + handler)
        registry.addHandler(handler, "/echo.ws").let {
            //            it.withSockJS()
            it.addInterceptors(HandshakeInterceptor())
            it.setAllowedOrigins("http://localhost")
        }
    }

    @Bean
    open fun HandshakeInterceptor() = object : HttpSessionHandshakeInterceptor() {

        @Throws(Exception::class)
        override fun beforeHandshake(request: ServerHttpRequest?, response: ServerHttpResponse?,
                                     wsHandler: WebSocketHandler?, attributes: Map<String, Any>): Boolean {
            // 解决The extension [x-webkit-deflate-frame] is not supported问题
            if (request!!.headers.containsKey("Sec-WebSocket-Extensions")) {
                request.headers.set("Sec-WebSocket-Extensions", "permessage-deflate")
            }
            return super.beforeHandshake(request, response, wsHandler, attributes)
        }
    }

}