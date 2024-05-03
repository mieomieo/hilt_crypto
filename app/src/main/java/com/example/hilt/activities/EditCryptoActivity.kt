package com.example.hilt.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.hilt.R
import com.example.hilt.databinding.ActivityEditCryptoBinding
import com.example.hilt.model.Crypto
import com.example.hilt.viewmodel.CryptoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditCryptoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditCryptoBinding

    private val viewModel: CryptoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditCryptoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val Crypto = intent?.getParcelableExtra<Crypto>("DATA")
        binding.etName.text= Editable.Factory.getInstance().newEditable(Crypto?.name)
        binding.etPrice.text= Editable.Factory.getInstance().newEditable(Crypto?.price)
        binding.etUrl.text= Editable.Factory.getInstance().newEditable(Crypto?.url)
        Glide.with(binding.imageView.context)
            .load(Crypto?.url ?: "")
            .error(R.drawable.img)
            .into(binding.imageView);
//        binding.btSave.setOnClickListener{finish()}

        binding.btSave.setOnClickListener{
//            val newCrypto = Crypto(binding.etName.text.toString(), binding.etEmail.text.toString(),  binding.etPhoneNumber.text.toString())
            Crypto?.name = binding.etName.text.toString()
            Crypto?.price = binding.etPrice.text.toString()
            Crypto?.url = binding.etUrl.text.toString()
            updateCrypto(it,Crypto)
            val resultIntent = Intent().apply {
                putExtra("EDIT_DATA", Crypto)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
    private fun updateCrypto(it: View?, Crypto:Crypto?) {
        if (Crypto != null) {
            viewModel.updateCrypto(Crypto)
            Toast.makeText(this@EditCryptoActivity, "Updated successfully", Toast.LENGTH_SHORT).show()
        }
    }

}