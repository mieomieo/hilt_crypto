package com.example.hilt.repository

import com.example.hilt.database.dao.ContactDao
import com.example.hilt.model.Contact
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContactRepository @Inject constructor(private val contactDao: ContactDao) {
    suspend fun insertContact(contact: Contact) = contactDao.insertContact(contact)
    suspend fun updateContact(contact: Contact) = contactDao.updateContact(contact)
    suspend fun deleteContact(contact: Contact) = contactDao.deleteContact(contact)
//    fun getAllContact(): LiveData<List<Contact>> = contactDao.getAllContact()

    suspend fun getFlowAllContact(): Flow<List<Contact>> {
        return contactDao.getFlowAllContact()
    }

}
