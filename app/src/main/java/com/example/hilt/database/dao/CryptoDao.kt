package com.example.hilt.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.hilt.model.Crypto
import kotlinx.coroutines.flow.Flow

@Dao
interface CryptoDao {
    @Insert
    suspend fun insertCrypto(crypto: Crypto)

    @Update
    suspend fun updateCrypto(crypto: Crypto)

    @Delete
    suspend fun deleteCrypto(crypto: Crypto)

//    @Query("SELECT * FROM Crypto_table")
//    fun getAllCrypto(): LiveData<List<Crypto>>

    @Query("Select * from Crypto_table")
    fun getFlowAllCrypto(): Flow<List<Crypto>>

//    @Query("SELECT * FROM Crypto_table WHERE name_col=:name")
//    fun getCryptoByName(name:String):LiveData<List<Crypto>>
}