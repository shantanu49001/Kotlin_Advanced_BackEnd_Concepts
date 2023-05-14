package com.devst.a13room.Db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuoteDao {

    @Insert
    suspend fun addqUOTES(quotes:List<com.devst.a13room.Models.Result>)

    @Query("SELECT * FROM quote")   //this will evetually return to vm nt live d isliye suspend
    suspend fun getQuotes():List<com.devst.a13room.Models.Result>
}