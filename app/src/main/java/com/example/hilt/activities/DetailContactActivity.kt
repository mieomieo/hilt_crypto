package com.example.hilt.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.hilt.databinding.ActivityDetailContactBinding
import com.example.hilt.model.Contact

class DetailContactActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetailContactBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var contact = intent?.getParcelableExtra<Contact>("DETAIL_CONTACT")
        binding.tvName.text = contact?.name
        binding.tvEmail.text = contact?.email
        binding.tvPhoneNumber.text = contact?.phoneNumber

        val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data?.getParcelableExtra<Contact>("EDIT_DATA")
                // Handle the received data here
                binding.tvName.text = "Name: " + data?.name ?: ""
                binding.tvEmail.text = "Email: " + data?.email ?: ""
                binding.tvPhoneNumber.text = "Phone Number: " + data?.phoneNumber ?: ""
                contact = data
            }
        }
        binding.btFloatEdit.setOnClickListener{
            val intent = Intent(this, EditContactActivity::class.java)
            intent.putExtra("DATA", contact)
            startForResult.launch(intent)
        }
        binding.btCall.setOnClickListener{
            callContact(contact)
        }

    }
    private fun callContact(contact: Contact?) {
        val dialIntent = Intent(Intent.ACTION_DIAL)
        dialIntent.data = Uri.parse("tel:${contact?.phoneNumber}")
        startActivity(dialIntent)
    }
}