package com.example.hilt.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hilt.database.dao.CryptoDao
import com.example.hilt.model.Crypto


@Database(entities = [Crypto::class], version = 1)
abstract class CryptoDatabase : RoomDatabase() {
    abstract fun CryptoDao():CryptoDao
}