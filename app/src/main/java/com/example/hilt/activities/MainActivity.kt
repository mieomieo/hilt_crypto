package com.example.hilt.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hilt.adapter.ContactAdapter
import com.example.hilt.adapter.ContactSearchAdapter
import com.example.hilt.databinding.ActivityMainBinding
import com.example.hilt.model.Contact
import com.example.hilt.viewmodel.ContactViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.Normalizer
import java.util.regex.Pattern
import kotlin.random.Random

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val contactViewModel: ContactViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initEvents()
        initControls()

    }

    private fun initControls() {
        val adapterContact = ContactAdapter(this@MainActivity, onItemClick, onItemDelete)
        val adapterSearchContact =
            ContactSearchAdapter(this@MainActivity, onItemClick, onItemDelete)
        binding.rvContacts.setHasFixedSize(true)
        binding.rvContacts.layoutManager = LinearLayoutManager(this)
        binding.rvContacts.adapter = adapterContact

        binding.recyclerViewListSearchContacts.setHasFixedSize(true)
        binding.recyclerViewListSearchContacts.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewListSearchContacts.adapter = adapterSearchContact


        binding.searchView.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?, start: Int, count: Int, after: Int
            ) {
                // No action needed here
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val text = getTextSearch(s.toString())
                lifecycleScope.launch {
                    contactViewModel.getFlowAllContact().collect { list ->

                        val filteredContact = list.filter {
                            getTextSearch(it.name).contains(
                                text, ignoreCase = true
                            )
                        }
                        adapterSearchContact.updateContact(filteredContact)
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // No action needed here
            }
        })

        lifecycleScope.launch {
            contactViewModel.apply {
                getFlowAllContact().collect { list ->
                    adapterContact.setContacts(list)
                }
            }
        }
    }

    private fun initEvents() {
        binding.btFloat.setOnClickListener {
            val intent = Intent(this, AddContactActivity::class.java)
            startActivity(intent)

        }
    }


    fun generateRandomEmail(): String {
        val alphabet = "abcdefghijklmnopqrstuvwxyz"
        val nameLength = (5..6).random()
        val randomName = (1..nameLength)
            .map { alphabet.random() }
            .joinToString("")

        return "$randomName@gmail.com"
    }

    fun generateRandomPhoneNumber(): String {
        val countryCode = "+84"
        val areaCode = "${Random.nextInt(100, 1000)}"
        val prefix = "${Random.nextInt(100, 1000)}"
        val lineNumber = "${Random.nextInt(1000, 10000)}"
        return "$countryCode$areaCode$prefix$lineNumber"
    }

    fun generateRandomName(): String {
        val alphabet = "abcdefghijklmnopqrstuvwxyz"
        val nameLength = (5..6).random()
        return (1..nameLength)
            .map { alphabet.random() }
            .joinToString("")
    }

    fun getTextSearch(input: String?): String {
        val nfdNormalizedString = Normalizer.normalize(input, Normalizer.Form.NFD)
        val pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+")
        return pattern.matcher(nfdNormalizedString).replaceAll("")
    }

    private val onItemClick: (Contact) -> Unit = {
        val intent = Intent(this@MainActivity, DetailContactActivity::class.java)
        intent.putExtra("DETAIL_CONTACT", it)
        startActivity(intent)
    }
    private val onItemDelete: (Contact) -> Unit = {
        contactViewModel.deleteContact(it)
    }
}
