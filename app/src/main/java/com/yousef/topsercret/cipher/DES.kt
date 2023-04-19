package com.yousef.topsercret.cipher

import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import java.util.Base64

class DES {
    val massage = "This key should be 8 bytes in length, since the DES algorithm requires a 64-bit key.\n" +
            "Check that the input parameter is not empty"
    fun encryptWithDES(key: String, plainText: String): String {
        val keySpec = SecretKeySpec(key.toByteArray(), "DES")
        val iv = ByteArray(8) // default IV of all zeroes
        val ivSpec = IvParameterSpec(iv)
        val cipher = Cipher.getInstance("DES/CBC/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec)
        val cipherText = cipher.doFinal(plainText.toByteArray())
        return Base64.getEncoder().encodeToString(cipherText)
    }


    fun decryptWithDES(key: String, cipherText: String): String {
        val keySpec = SecretKeySpec(key.toByteArray(), "DES")
        val iv = ByteArray(8) // default IV of all zeroes
        val ivSpec = IvParameterSpec(iv)
        val cipher = Cipher.getInstance("DES/CBC/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec)
        val plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText))
        return String(plainText)
    }

}