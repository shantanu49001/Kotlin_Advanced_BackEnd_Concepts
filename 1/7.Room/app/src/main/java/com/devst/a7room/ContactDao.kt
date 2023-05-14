package com.devst.a7room

import androidx.lifecycle.LiveData
import androidx.room.*

//this interface down't know about the table it jubasest know the operations params and return type
//isko yable ye sabke baare me pta chalega from db

//db is also long running task -->not in main thread

//now since thises functon can't run on main thread use suspend before them
//these functon won;t run on man thrwad--->suspend

@Dao
interface ContactDao {

    @Insert
  suspend  fun insertConact(contact: Contact)

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

//get operations are all live data
    //live data apne aap bg thread me run kr deta hai
    //we don;t need to add suspend


    @Query("SELECT * FROM contact")
    fun getContact():LiveData<List<Contact>>
    //not that tble me data Conatct hai and we have to return list of that on;y


}