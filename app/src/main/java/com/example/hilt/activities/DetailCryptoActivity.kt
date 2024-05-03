package com.example.hilt.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.hilt.R
import com.example.hilt.databinding.ActivityDetailCryptoBinding
import com.example.hilt.model.Crypto

class DetailCryptoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailCryptoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCryptoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var Crypto = intent?.getParcelableExtra<Crypto>("DETAIL_Crypto")
        binding.tvName.text = Crypto?.name
        binding.tvEmail.text = Crypto?.price
        binding.tvPhoneNumber.text = Crypto?.url
        Glide.with(binding.imageView.context)
            .load(Crypto?.url ?: "")
            .error(R.drawable.img)
            .into(binding.imageView);
        val startForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val data = result.data?.getParcelableExtra<Crypto>("EDIT_DATA")
                    binding.tvName.text = "Name: " + data?.name ?: ""
                    binding.tvEmail.text = "Email: " + data?.price ?: ""
                    binding.tvPhoneNumber.text = "Phone Number: " + data?.url ?: ""
                    Crypto = data
                }
            }
        binding.btFloatEdit.setOnClickListener {
            val intent = Intent(this, EditCryptoActivity::class.java)
            intent.putExtra("DATA", Crypto)
            startForResult.launch(intent)
        }
    }

}