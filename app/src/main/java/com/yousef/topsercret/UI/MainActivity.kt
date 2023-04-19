package com.yousef.topsercret.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.yousef.topsercret.R
import com.yousef.topsercret.cipher.*
import com.yousef.topsercret.databinding.ActivityMainBinding
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers.aes

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var input: String
    lateinit var keyx: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_TopSercret)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var checkRadioencryption = true
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radio_encrypt -> {
                    checkRadioencryption = true
                }
                R.id.radio_decrypt -> {
                    checkRadioencryption = false
                }
            }
        }

        binding.buttonTranslate.setOnClickListener {
            binding.infoText.visibility = View.GONE
            keyx = binding.editTextKey.text.toString()
            input = binding.editTextInput.text.toString()


            val selectedEncryptionAlgorithm = binding.spinnerEncryption.selectedItem.toString()
            when (selectedEncryptionAlgorithm) {
                "Playfair" -> {
                    try {
                        if (checkRadioencryption) {
                            var playfair = Playfair().Playfairencrypt(keyx, input)
                            binding.textViewResult.text = playfair
                        } else {
                            var playfair = Playfair().playfairDecrypt(keyx, input)
                            binding.textViewResult.text = playfair
                        }
                    } catch (e: java.lang.Exception) {
                        showInfo(Playfair().massage)
                    }

                }
                "AES" -> {
                    try {
                        if (checkRadioencryption) {
                            var aes = AES().encryptWithAES(keyx, input)
                            binding.textViewResult.text = aes
                        } else {
                            var aes = AES().decryptWithAES(keyx, input)
                            binding.textViewResult.text = aes
                        }
                    } catch (e: java.lang.Exception) {
                        showInfo(AES().massage)
                    }
                }
                "DES" -> {
                    try {
                        if (checkRadioencryption) {
                            var des = DES().encryptWithDES(keyx, input)
                            binding.textViewResult.text = des
                        } else {
                            var des = DES().decryptWithDES(keyx, input)
                            binding.textViewResult.text = des
                        }
                    } catch (e: java.lang.Exception) {
                        showInfo(DES().massage)
                    }


                }
                "3DES" -> {
                    try {
                        if (checkRadioencryption) {
                            var trides = TripleDES().encryptWith3DES(keyx, input)
                            binding.textViewResult.text = trides
                        } else {
                            var trides = TripleDES().decryptWith3DES(keyx, input)
                            binding.textViewResult.text = trides
                        }
                    } catch (e: java.lang.Exception) {
                        showInfo(TripleDES().massage)
                    }
                }
                "Vigenere" -> {
                    try{
                    if (checkRadioencryption) {
                        var vigenere = Vigenere().encryptWithVigenere(keyx, input)
                        binding.textViewResult.text = vigenere
                    } else {
                        var vigenere = Vigenere().decryptWithVigenere(keyx, input)
                        binding.textViewResult.text = vigenere
                    }
                    } catch (e: java.lang.Exception) {
                        showInfo(Vigenere().massage)
                    }
                }
                else -> {
                    try{
                    if (checkRadioencryption) {
                        var caeser = Caesar().encryptWithCaesar(keyx.toInt(), input)
                        binding.textViewResult.text = caeser
                    } else {
                        var caeser = Caesar().decryptWithCaesar(keyx.toInt(), input)
                        binding.textViewResult.text = caeser
                    }
                    } catch (e: java.lang.Exception) {
                        showInfo(Caesar().massage)
                    }
                }
            }


        }



        binding.clearTextButton.setOnClickListener {
            setClear()
        }

    }

    fun setClear() {
        binding.editTextKey.setText("")
        binding.editTextInput.setText("")
        binding.textViewResult.setText("")
    }

    fun showInfo(massage: String) {
        binding.infoText.text = massage
        binding.infoText.visibility = View.VISIBLE
        binding.textViewResult.setText("")
    }

}

