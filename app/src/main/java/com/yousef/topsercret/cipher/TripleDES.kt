package com.yousef.topsercret.cipher


import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import java.util.Base64

class TripleDES {
    val massage = "The key should be 24 bytes in length, since the 3DES algorithm requires a 192-bit key.\n" +
            "Check that the input parameter is not empty"
    fun encryptWith3DES(key: String, plainText: String): String {
        val keySpec = SecretKeySpec(key.toByteArray(), "DESede")
        val iv = ByteArray(8) // default IV of all zeroes
        val ivSpec = IvParameterSpec(iv)
        val cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec)
        val cipherText = cipher.doFinal(plainText.toByteArray())
        return Base64.getEncoder().encodeToString(cipherText)
    }

//    val key = "1234567890123456"
//    val plainText = "Hello, world!"
//    val cipherText = encryptWithAES(key, plainText)
//    println(cipherText) // output: "aKVn9RmW8+ff2QJrK1rL4w=="

    fun decryptWith3DES(key: String, cipherText: String): String {
        val keySpec = SecretKeySpec(key.toByteArray(), "DESede")
        val iv = ByteArray(8) // default IV of all zeroes
        val ivSpec = IvParameterSpec(iv)
        val cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec)
        val plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText))
        return String(plainText)
    }
//    Here's an example of how to use the function:
//    val key = "1234567890123456"
//    val cipherText = "6R8zG7HwJmt2z+KhJQg9Zg=="
//    val plainText = decryptWithAES(key, cipherText)
//    println(plainText) // output: "Hello, world!"

}