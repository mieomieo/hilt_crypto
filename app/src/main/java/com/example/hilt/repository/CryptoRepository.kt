package com.example.hilt.repository

import com.example.hilt.database.dao.CryptoDao
import com.example.hilt.model.Crypto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CryptoRepository @Inject constructor(private val CryptoDao: CryptoDao) {
    suspend fun insertCrypto(crypto: Crypto) = CryptoDao.insertCrypto(crypto)
    suspend fun updateCrypto(crypto: Crypto) = CryptoDao.updateCrypto(crypto)
    suspend fun deleteCrypto(crypto: Crypto) = CryptoDao.deleteCrypto(crypto)
//    fun getAllCrypto(): LiveData<List<Crypto>> = CryptoDao.getAllCrypto()

    suspend fun getFlowAllCrypto(): Flow<List<Crypto>> {
        return CryptoDao.getFlowAllCrypto()
    }

}
