package com.example.imtihon6_modul.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.imtihon6_modul.modul.Card

@Dao
interface CardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNote(note: Card)

    @Query("SELECT * FROM card_table")
    fun getNote(): List<Card>

    @Query("UPDATE card_table SET is_boolean = :is_boolean WHERE id = :id")
    fun updateCard(id: Int, is_boolean: Boolean): Int

    @Query("select * from card_table where is_boolean=0")
    fun getOflinePost(): List<Card>
}
