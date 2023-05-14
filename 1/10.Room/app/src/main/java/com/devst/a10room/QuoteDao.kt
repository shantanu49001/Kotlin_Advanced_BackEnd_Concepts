package com.devst.a10room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuoteDao {

    //doean't need to be suspend
    @Query("SELECT *FROM Quote")
    fun getQuotes():LiveData<List<Quote>>

    //can be called only in bg enviornment
    @Insert
    suspend fun insertQuote(quote: Quote)



}