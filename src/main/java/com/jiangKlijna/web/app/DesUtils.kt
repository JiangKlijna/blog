package com.jiangKlijna.web.app

import javax.crypto.Cipher
import java.security.Key

/**
 * Created by leil7 on 2017/6/14. blog
 */
class DesUtils
/**
 * @param secretKey 加密解密使用的密钥
 */
@JvmOverloads constructor(secretKey: String = DesUtils.defaultSecretKey) {
    private var encryptCipher: Cipher? = null //加密器
    private var decryptCipher: Cipher? = null //解密器

    init {
        val key: Key
        try {
            key = getKey(secretKey.toByteArray())
            encryptCipher = Cipher.getInstance("DES")
            encryptCipher!!.init(Cipher.ENCRYPT_MODE, key)
            decryptCipher = Cipher.getInstance("DES")
            decryptCipher!!.init(Cipher.DECRYPT_MODE, key)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * 加密 (逻辑: 1. 将要加密的字符串转换为字节数组(byte array)<br></br>
     * 2. 将第一步的字节数组作为输入使用加密器(Cipher)的doFinal方法进行加密, 返回字节数组<br></br>
     * 3. 把加密后的字节数组转换成十六进制的字符串)<br></br>

     * @param strIn 要加密的字符串
     * *
     * @return 返回加密后的十六进制字符串
     * *
     * @throws Exception
     */
    @Throws(Exception::class)
    fun encrypt(strIn: String): String {
        return byteArr2HexStr(encrypt(strIn.toByteArray()))
    }

    @Throws(Exception::class)
    fun encrypt(arrB: ByteArray): ByteArray {
        return encryptCipher!!.doFinal(arrB)
    }

    /**
     * 解密 (逻辑: 1. 把加密后的十六进制字符串转换成字节数组(byte array)<br></br>
     * 2. 将第一步的字节数组作为输入使用加密器(Cipher)的doFinal方法进行解密, 返回字节数组(byte array)<br></br>
     * 3. 把解密后的字节数组转换成字符串)<br></br>

     * @param strIn
     * *
     * @return
     * *
     * @throws Exception
     */
    @Throws(Exception::class)
    fun decrypt(strIn: String): String {
        return String(decrypt(hexStr2ByteArr(strIn)))
    }

    @Throws(Exception::class)
    fun decrypt(arrB: ByteArray): ByteArray {
        return decryptCipher!!.doFinal(arrB)
    }

    @Throws(Exception::class)
    private fun getKey(arrBTmp: ByteArray): Key {
        // 创建一个空的8位字节数组（默认值为0）
        val arrB = ByteArray(8)
        // 将原始字节数组转换为8位
        var i = 0
        while (i < arrBTmp.size && i < arrB.size) {
            arrB[i] = arrBTmp[i]
            i++
        }
        // 生成密钥
        val key = javax.crypto.spec.SecretKeySpec(arrB, "DES")
        return key
    }

    companion object {
        private const val defaultSecretKey = "jiangKlijna" //默认密钥

        val du: DesUtils = DesUtils()

        @Throws(Exception::class)
        fun byteArr2HexStr(arrB: ByteArray): String {
            val iLen = arrB.size
            // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
            val sb = StringBuffer(iLen * 2)
            for (i in 0..iLen - 1) {
                var intTmp = arrB[i].toInt()
                // 把负数转换为正数
                while (intTmp < 0) {
                    intTmp = intTmp + 256
                }
                // 小于0F的数需要在前面补0
                if (intTmp < 16) {
                    sb.append("0")
                }
                sb.append(Integer.toString(intTmp, 16))
            }
            return sb.toString()
        }

        @Throws(Exception::class)
        fun hexStr2ByteArr(strIn: String): ByteArray {
            val arrB = strIn.toByteArray()
            val iLen = arrB.size
            // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
            val arrOut = ByteArray(iLen / 2)
            var i = 0
            while (i < iLen) {
                val strTmp = String(arrB, i, 2)
                arrOut[i / 2] = Integer.parseInt(strTmp, 16).toByte()
                i = i + 2
            }
            return arrOut
        }
    }
}