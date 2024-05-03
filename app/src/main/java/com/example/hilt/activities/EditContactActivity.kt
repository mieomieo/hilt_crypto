package com.example.hilt.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.hilt.databinding.ActivityEditContactBinding
import com.example.hilt.model.Contact
import com.example.hilt.viewmodel.ContactViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditContactBinding

    private val viewModel: ContactViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val contact = intent?.getParcelableExtra<Contact>("DATA")
        binding.etName.text= Editable.Factory.getInstance().newEditable(contact?.name)
        binding.etEmail.text= Editable.Factory.getInstance().newEditable(contact?.email)
        binding.etPhoneNumber.text= Editable.Factory.getInstance().newEditable(contact?.phoneNumber)
//        Glide.with(binding.imageView.context)
//            .load(contact?.image ?: "")
//            .error(R.drawable.profile)
//            .into(binding.imageView);
//        binding.btSave.setOnClickListener{finish()}

        binding.btSave.setOnClickListener{
//            val newContact = Contact(binding.etName.text.toString(), binding.etEmail.text.toString(),  binding.etPhoneNumber.text.toString())
            contact?.name = binding.etName.text.toString()
            contact?.email = binding.etEmail.text.toString()
            contact?.phoneNumber = binding.etPhoneNumber.text.toString()
            updateContact(it,contact)
            val resultIntent = Intent().apply {
                putExtra("EDIT_DATA", contact)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
    private fun updateContact(it: View?, contact:Contact?) {
        if (contact != null) {
//            contact.name = binding.etName.text.toString()
//            contact.email = binding.etEmail.text.toString()
//            contact.phoneNumber = binding.etPhoneNumber.text.toString()

//            contact.image = binding.etImage.text.toString()
            viewModel.updateContact(contact)
            Toast.makeText(this@EditContactActivity, "Updated successfully", Toast.LENGTH_SHORT).show()
        }
    }

}