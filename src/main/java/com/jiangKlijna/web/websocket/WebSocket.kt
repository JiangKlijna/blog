package com.jiangKlijna.web.websocket

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry
import org.springframework.web.socket.handler.TextWebSocketHandler
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor
import java.util.*

/**
 * Created by leil7 on 2017/6/6.
 */
@Configuration
@EnableWebSocket
open class WebSocketConfig : WebSocketConfigurer {

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        println("registerWebSocketHandlers")
        registry.addHandler(myHandler(), "/echo.ws").let {
            //            it.withSockJS()
            it.addInterceptors(HandshakeInterceptor())
            it.setAllowedOrigins("http://localhost")
        }
    }

    @Bean
    open fun myHandler(): WebSocketHandler {
        return ChatWebSocketHandler()
    }

}

class HandshakeInterceptor : HttpSessionHandshakeInterceptor() {

    @Throws(Exception::class)
    override fun beforeHandshake(request: ServerHttpRequest?,
                                 response: ServerHttpResponse?, wsHandler: WebSocketHandler?,
                                 attributes: Map<String, Any>): Boolean {
        // 解决The extension [x-webkit-deflate-frame] is not supported问题
        if (request!!.headers.containsKey("Sec-WebSocket-Extensions")) {
            request.headers.set("Sec-WebSocket-Extensions", "permessage-deflate")
        }
        return super.beforeHandshake(request, response, wsHandler, attributes)
    }
}

class ChatWebSocketHandler : TextWebSocketHandler() {
    //接收文本消息，并发送出去
    @Throws(Exception::class)
    override fun handleTextMessage(session: WebSocketSession?, message: TextMessage?) {

//        chatTextMessageHandler(message!!.payload)
        super.handleTextMessage(session, message)
        session!!.sendMessage(TextMessage("Server:connected OK!"))
    }

    //连接建立后处理
    @Throws(Exception::class)
    override fun afterConnectionEstablished(session: WebSocketSession?) {
        sessions.add(session)
        //处理离线消息
    }

    //抛出异常时处理
    @Throws(Exception::class)
    override fun handleTransportError(session: WebSocketSession?, exception: Throwable?) {
        if (session!!.isOpen) session.close()
        sessions.remove(session)
    }

    //连接关闭后处理
    @Throws(Exception::class)
    override fun afterConnectionClosed(session: WebSocketSession?, closeStatus: CloseStatus?) {
        sessions.remove(session)
    }

    override fun supportsPartialMessages(): Boolean {
        return false
    }

    companion object {
        private val sessions = Collections.synchronizedList(ArrayList<WebSocketSession>())
    }
}