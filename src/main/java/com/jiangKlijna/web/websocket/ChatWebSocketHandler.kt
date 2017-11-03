package com.jiangKlijna.web.websocket

import com.jiangKlijna.web.bean.Message
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.util.*
import javax.annotation.Resource

/**
 * Created by leil7 on 2017/6/6.
 */
class ChatWebSocketHandler : TextWebSocketHandler() {

	@Resource(name = "redisTemplate")
	var rt: RedisTemplate<String, Message>? = null

	//接收文本消息，并发送出去
	override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
		session.sendMessage(message)
	}

	//连接建立后处理
	override fun afterConnectionEstablished(session: WebSocketSession) {
		sessions.add(session)
		session.sendMessage(TextMessage("Server:connected OK!"))
	}

	//抛出异常时处理
	override fun handleTransportError(session: WebSocketSession, exception: Throwable?) {
		if (session.isOpen) session.close()
		sessions.remove(session)
	}

	//连接关闭后处理
	override fun afterConnectionClosed(session: WebSocketSession, closeStatus: CloseStatus?) {
		sessions.remove(session)
	}

	override fun supportsPartialMessages() = false

	companion object {
		private val sessions = Collections.synchronizedList(ArrayList<WebSocketSession>())
	}
}