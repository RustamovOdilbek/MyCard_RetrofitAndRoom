package com.example.imtihon6_modul.database

import android.app.Application
import com.example.imtihon6_modul.manager.RoomManager
import com.example.imtihon6_modul.modul.Card

class CardRepository {
    var cardDao: CardDao
    private val TAG: String = CardRepository::class.java.simpleName

    constructor(application: Application){
        val db = RoomManager.getDatabase(application)
        cardDao = db.noteDao()
    }

    fun getCards(): List<Card>{
        return cardDao.getNote()
    }

    fun saveCard(card: Card){
        cardDao.saveNote(card)
    }

    fun updateCard(id: Int, is_boolean: Boolean){
        cardDao.updateCard(id, is_boolean)
    }
}