package com.example.hilt.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hilt.model.Crypto
import com.example.hilt.repository.CryptoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoViewModel @Inject constructor(private val cryptoRepository: CryptoRepository) : ViewModel() {

    private var _allCryptosLiveData: MutableLiveData<List<Crypto>> = MutableLiveData()
    val allCryptosLiveData: LiveData<List<Crypto>>
        get() = _allCryptosLiveData



    fun insertCrypto(crypto: Crypto) = viewModelScope.launch {
        cryptoRepository.insertCrypto(crypto)
    }

    fun updateCrypto(crypto: Crypto) = viewModelScope.launch {
        cryptoRepository.updateCrypto(crypto)
    }

    fun deleteCrypto(crypto: Crypto) = viewModelScope.launch {
        cryptoRepository.deleteCrypto(crypto)
    }
    suspend fun getFlowAllCrypto():kotlinx.coroutines.flow.Flow<List<Crypto>> = cryptoRepository.getFlowAllCrypto().flowOn(
        Dispatchers.IO)
//    fun getAllCrypto(): LiveData<List<Crypto>> = CryptoRepository.getAllCrypto()


}