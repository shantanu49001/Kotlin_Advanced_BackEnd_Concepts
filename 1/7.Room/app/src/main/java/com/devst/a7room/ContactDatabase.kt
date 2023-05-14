package com.devst.a7room

import androidx.room.Database
import androidx.room.RoomDatabase

//add all the tables associated with this db


//abstr ka object bange a
//for db changes in new project updated project
@Database(entities = [Contact::class], version = 1 )  //table link
abstract class ContactDatabase:RoomDatabase() {

    //linking the dao to work on collections of tables associated
    //doore tables ke dao bhi idhar add

    abstract fun contactDao():ContactDao

    //ek app me ek hi db hona hai isliye singleton use krte
    //we can also use it directly
    //basically we forefully make it single istance


}