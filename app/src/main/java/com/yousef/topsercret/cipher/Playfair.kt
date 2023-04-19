package com.yousef.topsercret.cipher

class Playfair {
    val massage = "This key should be a string of letters without spaces or punctuation or digits.\n" +
            "Check that the input parameter is not empty"
    fun playfairDecrypt( key: String ,ciphertext: String): String {
        // Generate the key table
        val keyT = generateKeyTable(key)

        // Convert the ciphertext to lowercase and remove spaces
        val cipher = ciphertext.toLowerCase().replace(" ", "")

        // Check if the ciphertext length is odd and append a dummy character if necessary
        val cipherLength = cipher.length
        val plain = if (cipherLength % 2 == 0) cipher.toCharArray() else (cipher + "z").toCharArray()

        // Decrypt the plaintext
        for (i in 0 until plain.size step 2) {
            val a = findPosition(keyT, plain[i])
            val b = findPosition(keyT, plain[i + 1])

            if (a[0] == b[0]) {
                plain[i] = keyT[a[0]][mod5(a[1] - 1)]
                plain[i + 1] = keyT[b[0]][mod5(b[1] - 1)]
            } else if (a[1] == b[1]) {
                plain[i] = keyT[mod5(a[0] - 1)][a[1]]
                plain[i + 1] = keyT[mod5(b[0] - 1)][b[1]]
            } else {
                plain[i] = keyT[a[0]][b[1]]
                plain[i + 1] = keyT[b[0]][a[1]]
            }
        }

        // Remove trailing dummy character if necessary and return the decrypted plaintext
        return String(plain.copyOf(cipherLength))
    }

    fun generateKeyTable(key: String): Array<CharArray> {
        // Remove duplicate characters and replace "j" with "i"
        val cleanedKey = key.toLowerCase().replace("j", "i").toCharArray().distinct().toCharArray()

        // Initialize the key table with zeros
        val keyT = Array(5) { CharArray(5) }
        for (i in 0..4) {
            for (j in 0..4) {
                keyT[i][j] = '0'
            }
        }

        // Fill the key table with the cleaned key
        var i = 0
        var j = 0
        for (k in cleanedKey.indices) {
            keyT[i][j] = cleanedKey[k]
            j++
            if (j == 5) {
                i++
                j = 0
            }
        }

        // Fill the remaining cells with the remaining alphabet letters
        var c = 'a'
        while (i < 5) {
            while (j < 5) {
                if (!cleanedKey.contains(c) && c != 'j') {
                    keyT[i][j] = c
                    j++
                }
                c++
            }
            j = 0
            i++
        }

        return keyT
    }

    fun findPosition(keyT: Array<CharArray>, c: Char): IntArray {
        val pos = IntArray(2)
        for (i in 0..4) {
            for (j in 0..4) {
                if (keyT[i][j] == c) {
                    pos[0] = i
                    pos[1] = j
                    return pos
                }
            }
        }
        return pos
    }

    fun mod5(a: Int): Int {
        var temp = a
        if (temp < 0) {
            temp += 5
        }
        return temp % 5
    }
    fun Playfairencrypt(key: String, plainText: String): String {
        // convert all the characters to lowercase
        var keyStr = key.toLowerCase()
        var plainTextStr = plainText.toLowerCase()

        // remove any non-alphabetic characters from the plaintext
        plainTextStr = plainTextStr.replace("[^a-z]".toRegex(), "")

        // create the key table
        val keyTable = Array(5) { CharArray(5) }
        val usedChars = mutableListOf<Char>()
        var row = 0
        var col = 0
        for (ch in keyStr) {
            if (!usedChars.contains(ch) && ch != 'j') {
                keyTable[row][col] = ch
                usedChars.add(ch)
                col++
                if (col == 5) {
                    col = 0
                    row++
                }
            }
        }
        for (ch in 'a'..'z') {
            if (!usedChars.contains(ch) && ch != 'j') {
                keyTable[row][col] = ch
                usedChars.add(ch)
                col++
                if (col == 5) {
                    col = 0
                    row++
                }
            }
        }

        // preprocess the plaintext
        var processedText = ""
        var i = 0
        while (i < plainTextStr.length) {
            val ch1 = plainTextStr[i]
            val ch2 = if (i + 1 < plainTextStr.length) plainTextStr[i + 1] else 'x'

            if (ch1 == ch2) {
                processedText += ch1.toString() + 'x'
                i++
            } else {
                processedText += ch1.toString() + ch2.toString()
                i += 2
            }
        }
        if (processedText.length % 2 != 0) {
            processedText += 'x'
        }

        // encrypt the processed text
        var cipherText = ""
        for (i in 0 until processedText.length step 2) {
            val ch1 = processedText[i]
            val ch2 = processedText[i + 1]

            var row1 = 0
            var col1 = 0
            var row2 = 0
            var col2 = 0
            for (r in 0..4) {
                for (c in 0..4) {
                    if (keyTable[r][c] == ch1) {
                        row1 = r
                        col1 = c
                    } else if (keyTable[r][c] == ch2) {
                        row2 = r
                        col2 = c
                    }
                }
            }

            if (row1 == row2) {
                cipherText += keyTable[row1][(col1 + 1) % 5].toString() + keyTable[row2][(col2 + 1) % 5].toString()
            } else if (col1 == col2) {
                cipherText += keyTable[(row1 + 1) % 5][col1].toString() + keyTable[(row2 + 1) % 5][col2].toString()
            } else {
                cipherText += keyTable[row1][col2].toString() + keyTable[row2][col1].toString()
            }
        }

        return cipherText
    }

}