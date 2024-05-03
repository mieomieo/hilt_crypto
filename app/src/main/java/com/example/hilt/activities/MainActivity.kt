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
import com.example.hilt.adapter.CryptoAdapter
import com.example.hilt.adapter.CryptoSearchAdapter
import com.example.hilt.databinding.ActivityMainBinding
import com.example.hilt.model.Crypto
import com.example.hilt.viewmodel.CryptoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.Normalizer
import java.util.regex.Pattern
import kotlin.random.Random

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val cryptoViewModel: CryptoViewModel by viewModels()
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
        val adapterCrypto = CryptoAdapter(this@MainActivity, onItemClick, onItemDelete)
        val adapterSearchCrypto =
            CryptoSearchAdapter(this@MainActivity, onItemClick, onItemDelete)
        binding.rvCryptos.setHasFixedSize(true)
        binding.rvCryptos.layoutManager = LinearLayoutManager(this)
        binding.rvCryptos.adapter = adapterCrypto

        binding.recyclerViewListSearchCryptos.setHasFixedSize(true)
        binding.recyclerViewListSearchCryptos.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewListSearchCryptos.adapter = adapterSearchCrypto


        binding.searchView.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?, start: Int, count: Int, after: Int
            ) {
                // No action needed here
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val text = getTextSearch(s.toString())
                lifecycleScope.launch {
                    cryptoViewModel.getFlowAllCrypto().collect { list ->

                        val filteredCrypto = list.filter {
                            getTextSearch(it.name).contains(
                                text, ignoreCase = true
                            )
                        }
                        adapterSearchCrypto.updateCrypto(filteredCrypto)
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // No action needed here
            }
        })

        lifecycleScope.launch {
            cryptoViewModel.apply {
                getFlowAllCrypto().collect { list ->
                    adapterCrypto.setCryptos(list)
                }
            }
        }
    }

    private fun initEvents() {
        binding.btFloat.setOnClickListener {
            val intent = Intent(this, AddCryptoActivity::class.java)
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

    private val onItemClick: (Crypto) -> Unit = {
        val intent = Intent(this@MainActivity, DetailCryptoActivity::class.java)
        intent.putExtra("DETAIL_Crypto", it)
        startActivity(intent)
    }
    private val onItemDelete: (Crypto) -> Unit = {
        cryptoViewModel.deleteCrypto(it)
    }
}
