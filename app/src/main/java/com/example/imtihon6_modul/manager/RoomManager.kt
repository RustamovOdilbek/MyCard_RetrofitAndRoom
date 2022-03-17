package com.example.imtihon6_modul.manager

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.imtihon6_modul.database.CardDao
import com.example.imtihon6_modul.modul.Card

@Database(entities = [Card::class], version = 1)
abstract class RoomManager: RoomDatabase() {

    abstract fun noteDao(): CardDao

    companion object{

        private var INSTANCE: RoomManager? = null

        fun getDatabase(context: Context): RoomManager{
            if (INSTANCE == null){
                synchronized(RoomManager::class.java){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, RoomManager::class.java, "card_table.db")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}