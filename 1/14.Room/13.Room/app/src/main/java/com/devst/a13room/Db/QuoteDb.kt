package com.devst.a13room.Db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Result::class],version=1)
abstract class QuoteDb :RoomDatabase(){
    abstract fun quoteDao():QuoteDao

    companion object{
        @Volatile
        private var INSTANCE:QuoteDb?=null
        fun getDb(context: Context):QuoteDb{
            if (INSTANCE==null){
             synchronized(this){
                 INSTANCE= Room.databaseBuilder(context,QuoteDb::class.java,"quoteDb").build()
             }
            }
            return INSTANCE!!
        }

    }
}