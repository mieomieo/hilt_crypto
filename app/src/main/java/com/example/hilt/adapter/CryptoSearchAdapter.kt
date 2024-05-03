package com.example.hilt.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hilt.R
import com.example.hilt.databinding.ItemCryptoBinding
import com.example.hilt.model.Crypto

class CryptoSearchAdapter(
    private val context: Context,
    private val onClick: (Crypto) -> Unit,
    private val onDelele: (Crypto) -> Unit
) : RecyclerView.Adapter<CryptoSearchAdapter.ViewHolder>() {
    private var Cryptos: List<Crypto> = listOf()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: ItemCryptoBinding = ItemCryptoBinding.bind(itemView)

        val btnDelete = binding.btnDelete
        val itemCrypto = binding.clView
        fun onBind(Crypto: Crypto) {
            binding.item = Crypto
            btnDelete.visibility = View.GONE
            itemCrypto.setOnClickListener {
                onClick(Crypto)
            }
            Glide.with(binding.imageCrypto.context)
                .load(Crypto.url ?: "")
                .error(R.drawable.img)
                .into(binding.imageCrypto);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_crypto, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(Cryptos[position])
    }

    fun updateCrypto(Cryptos: List<Crypto>) {
        this.Cryptos = Cryptos
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return Cryptos.size
    }

}