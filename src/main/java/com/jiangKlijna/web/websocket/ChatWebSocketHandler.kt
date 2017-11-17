package com.jiangKlijna.web.websocket

import com.fasterxml.jackson.databind.ObjectMapper
import com.jiangKlijna.web.bean.Message
import com.jiangKlijna.web.bean.User
import com.jiangKlijna.web.service.UserService
import org.springframework.data.redis.connection.MessageListener
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.io.ByteArrayInputStream
import java.io.ObjectInputStream
import java.util.concurrent.CopyOnWriteArrayList
import javax.annotation.Resource


/**
 * Created by leil7 on 2017/6/6.
 */
@Component("ChatWebSocketHandler")
class ChatWebSocketHandler : TextWebSocketHandler() {

    @Resource(name = "redisTemplate")
    val rt: RedisTemplate<String, Message>? = null

    @Resource(name = "userService")
    val us: UserService? = null

    //接收文本消息
    //只接受login信息,否则断开连接
    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        try {
            val cmd = mapper.readValue(message.payload, Command::class.java)
            if ("userid" !in session.attributes && !cmd.isLogin()) throw RuntimeException("not login")
            if (cmd.isLogin()) {
                val re = us!!.get(cmd.data["username"]!!)
                if (!re.isSucess()) throw RuntimeException("unknown username")
                session.attributes["userid"] = (re.data as User).id
            }
        } catch (e: Exception) {
            handleTransportError(session, e)
        }
    }

    //0.连接建立后处理
    override fun afterConnectionEstablished(session: WebSocketSession) {
        sessions.add(session)
//        session.sendMessage(TextMessage("Server:connected OK!"))
    }

    //1.连接关闭后处理
    override fun afterConnectionClosed(session: WebSocketSession, closeStatus: CloseStatus?) {
        sessions.remove(session)
    }

    //2.抛出异常时处理
    override fun handleTransportError(session: WebSocketSession, exception: Throwable?) {
        if (session.isOpen) session.close()
        if (session in sessions) sessions.remove(session)
    }


    companion object {
        private val mapper: ObjectMapper = ObjectMapper()
        private val sessions = CopyOnWriteArrayList<WebSocketSession>()
        private fun <T : java.io.Serializable> ByteArray.toObject(): T =
                ObjectInputStream(ByteArrayInputStream(this)).readObject() as T

        private val updateMessage = TextMessage("update")
    }

    /**
     *  msg.body为com.jiangKlijna.web.bean.Message
     *  rt.convertAndSend(String, Message)
     */
    class RedisMessageListener : MessageListener {
        @Throws
        override fun onMessage(msg: org.springframework.data.redis.connection.Message, p1: ByteArray?) {
            val m = msg.body.toObject<Message>()
            // 遍历所有session
            for (session in sessions) {
                if ("userid" !in session.attributes) continue
                if (m.touser != session.attributes["userid"]) continue
                session.sendMessage(updateMessage)
            }
        }
    }

    data class Command(var num: Int = 0, var data: Map<String, String> = emptyMap()) {
        // 是否为登陆命令
        val isLogin = fun() = num == 0
    }
}