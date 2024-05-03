package com.example.hilt.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Crypto_table")
class Crypto(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,
    @ColumnInfo(name = "name_col") var name: String = "",
    @ColumnInfo(name = "price_col") var price: String = "",
    @ColumnInfo(name = "url_col") var url: String = ""
) : Parcelable