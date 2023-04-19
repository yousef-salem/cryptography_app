package com.yousef.topsercret.cipher

class Caesar {
    val massage = "The key should be integer.\n" +
            "Check that the input parameter is not empty"
    fun encryptWithCaesar( shift: Int , plainText: String): String {
        val shiftedAlphabet = ('A'..'Z').map { ((it.toInt() - 'A'.toInt()
                + shift) % 26 + 'A'.toInt()).toChar() }.joinToString("")
        return plainText.toUpperCase().map { if (it.isLetter()) shiftedAlphabet[it - 'A'].toString() else it.toString() }
            .joinToString("")
    }
    fun decryptWithCaesar(shift: Int ,cipherText: String): String {
        val shiftedAlphabet = ('A'..'Z').map { ((it.toInt() - 'A'.toInt()
                + shift) % 26 + 'A'.toInt()).toChar() }.joinToString("")
        return cipherText.toUpperCase().map { if (it.isLetter()) ('A' + shiftedAlphabet.indexOf(it))
            .toString() else it.toString() }.joinToString("")
    }
}