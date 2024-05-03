package com.example.hilt.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hilt.model.Contact
import com.example.hilt.repository.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(private val contactRepository: ContactRepository) : ViewModel() {

    private var _allContactsLiveData: MutableLiveData<List<Contact>> = MutableLiveData()
    val allContactsLiveData: LiveData<List<Contact>>
        get() = _allContactsLiveData

    fun insertContact(contact: Contact) = viewModelScope.launch {
        contactRepository.insertContact(contact)
    }

    fun updateContact(contact: Contact) = viewModelScope.launch {
        contactRepository.updateContact(contact)
    }

    fun deleteContact(contact: Contact) = viewModelScope.launch {
        contactRepository.deleteContact(contact)
    }
    suspend fun getFlowAllContact():kotlinx.coroutines.flow.Flow<List<Contact>> = contactRepository.getFlowAllContact().flowOn(
        Dispatchers.IO)
//    fun getAllContact(): LiveData<List<Contact>> = contactRepository.getAllContact()


}