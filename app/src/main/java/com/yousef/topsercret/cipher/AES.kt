package com.yousef.topsercret.cipher

import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import java.util.Base64

class AES {
    val massage = "Ensure that the key parameter is a string of bytes that is a multiple of 8, and that it is used only for AES encryption.\n" +
            "Check that the input parameter is not empty"
    fun encryptWithAES(key: String, plainText: String): String {
        val keySpec = SecretKeySpec(key.toByteArray(), "AES")
        val iv = ByteArray(16) // default IV of all zeroes
        val ivSpec = IvParameterSpec(iv)
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec)
        val cipherText = cipher.doFinal(plainText.toByteArray())
        return Base64.getEncoder().encodeToString(cipherText)
    }

//    val key = "1234567890123456"
//    val plainText = "Hello, world!"
//    val cipherText = encryptWithAES(key, plainText)
//    println(cipherText) // output: "aKVn9RmW8+ff2QJrK1rL4w=="

    fun decryptWithAES(key: String, cipherText: String): String {
        val keySpec = SecretKeySpec(key.toByteArray(), "AES")
        val iv = ByteArray(16) // default IV of all zeroes
        val ivSpec = IvParameterSpec(iv)
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec)
        val plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText))
        return String(plainText)
    }
//    Here's an example of how to use the function:
//
//    val key = "1234567890123456"
//    val cipherText = "6R8zG7HwJmt2z+KhJQg9Zg=="
//    val plainText = decryptWithAES(key, cipherText)
//    println(plainText) // output: "Hello, world!"

}