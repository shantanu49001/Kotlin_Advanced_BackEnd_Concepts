package com.devst.a7room

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

//basic code simple
//extra things marked with e


@Database(entities = [Contact::class], version = 2)  //table link
@TypeConverters(Convertors::class)
abstract class ContactDatabase : RoomDatabase() {


    abstract fun contactDao(): ContactDao


    companion object {
        //migration object      older v to new
        val migration_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE contact ADD COLUMN isActive INTEGER NOT NULL DEFAULT(1)")
            }

        }


        @Volatile  //e   jaise hi instance pr kuch bhui upadye ho saare htreads ko upadte -->agar db destro hua hi to saare pr notify
        private var INSTANCE: ContactDatabase? =
            null  //agar db hoga to ye yahi return karega nhi hoga to naya banake karega

        //since the instance is private we need somthing to exoise t outside
        fun getDatabase(context: Context): ContactDatabase {

            if (INSTANCE == null) {
                //craete if null

                //main code tht build
                synchronized(this) {  //e. jan ek hi time pr n object banake ki koshish kre to ek baar me ek hi

                    //basic
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ContactDatabase::class.java,
                        "contactDb"
                    )
                        .addMigrations(migration_1_2)
                        .build()

                }
            }
            return INSTANCE!!   //!!-->when we are sue it won;t be null
        }
    }


}