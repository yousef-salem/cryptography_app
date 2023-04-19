package com.yousef.topsercret.cipher

class Vigenere {
    val massage = "This key should be a string of letters without spaces or punctuation or digits.\n" +
            "Check that the input parameter is not empty"
    fun encryptWithVigenere( key: String,plainText: String): String {
        val keyRepeated = key.toUpperCase().repeat(plainText.length / key.length + 1).substring(0, plainText.length)
        return plainText.toUpperCase().mapIndexed { index, char ->
            if (char.isLetter()) {
                val shiftedAlphabet = ('A'..'Z').toList().subList(keyRepeated[index] - 'A', 26) + ('A' until keyRepeated[index]).map { it }
                shiftedAlphabet[char - 'A'].toString()
            } else {
                char.toString()
            }
        }.joinToString("")
    }

    fun decryptWithVigenere( key: String,cipherText: String): String {
        val keyRepeated = key.toUpperCase().repeat(cipherText.length / key.length + 1).substring(0, cipherText.length)
        return cipherText.toUpperCase().mapIndexed { index, char ->
            if (char.isLetter()) {
                val shiftedAlphabet = ('A'..'Z').toList().subList(keyRepeated[index] - 'A', 26) + ('A' until keyRepeated[index]).map { it }
                ('A' + shiftedAlphabet.indexOf(char)).toString()
            } else {
                char.toString()
            }
        }.joinToString("")
    }
}