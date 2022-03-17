package com.example.imtihon6_modul.modul

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_table")
data class Card(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val card_id: String,
    val cvv: String,
    var is_boolean: Boolean,
    val `data`: String,
    val name: String
)