package com.example.hilt.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.hilt.model.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {
    @Insert
    suspend fun insertContact(contact: Contact)

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

//    @Query("SELECT * FROM contact_table")
//    fun getAllContact(): LiveData<List<Contact>>

    @Query("Select * from contact_table")
    fun getFlowAllContact(): Flow<List<Contact>>

//    @Query("SELECT * FROM contact_table WHERE name_col=:name")
//    fun getContactByName(name:String):LiveData<List<Contact>>
}