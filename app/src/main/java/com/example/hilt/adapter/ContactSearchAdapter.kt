package com.example.hilt.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hilt.R
import com.example.hilt.databinding.ItemContactBinding
import com.example.hilt.model.Contact

class ContactSearchAdapter(
    private val context: Context,
    private val onClick: (Contact) -> Unit,
    private val onDelele: (Contact) -> Unit
) : RecyclerView.Adapter<ContactSearchAdapter.ViewHolder>() {
    private var contacts: List<Contact> = listOf()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: ItemContactBinding = ItemContactBinding.bind(itemView)

        val btnDelete = binding.btnDelete
        val itemContact = binding.clView
        fun onBind(contact: Contact) {
            binding.item = contact
            btnDelete.visibility = View.GONE
            itemContact.setOnClickListener {
                onClick(contact)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(contacts[position])
    }
    fun updateContact(contacts:List<Contact>){
        this.contacts = contacts
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return contacts.size
    }

}