package com.example.hilt.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.hilt.databinding.ActivityAddCryptoBinding
import com.example.hilt.model.Crypto
import com.example.hilt.viewmodel.CryptoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCryptoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddCryptoBinding

    private val viewModel: CryptoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCryptoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btSubmit.setOnClickListener {
            createCrypto(it)
        }


    }

    private fun createCrypto(it: View?) {
        val name = binding.etName.text.toString()
        val url = binding.etUrl.text.toString()
        val price = binding.etPrice.text.toString()
        val data = Crypto(name = name, price = price, url = url)
        viewModel.insertCrypto(data)
        Toast.makeText(this@AddCryptoActivity, "Saved", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this@AddCryptoActivity, MainActivity::class.java))
    }

}