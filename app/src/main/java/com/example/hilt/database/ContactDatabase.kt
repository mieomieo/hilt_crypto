package com.example.hilt.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hilt.database.dao.ContactDao
import com.example.hilt.model.Contact


@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao():ContactDao
}