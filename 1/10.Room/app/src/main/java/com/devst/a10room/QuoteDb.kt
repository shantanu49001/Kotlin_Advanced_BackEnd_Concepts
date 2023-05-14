package com.devst.a10room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.sql.Types.NULL

@Database(entities = [Quote::class], version = 1)
abstract class QuoteDb:RoomDatabase() {

    abstract  fun getDao():QuoteDao


    companion object{
        @Volatile
        private var INSTANCE:QuoteDb?=null


        fun getDataBase(context: Context):QuoteDb{
            if (INSTANCE==null){

                synchronized(this){
                    INSTANCE= Room.databaseBuilder(context.applicationContext,QuoteDb::class.java,"QuoteDb").build()
                }

            }
            return INSTANCE!!
        }

    }

}