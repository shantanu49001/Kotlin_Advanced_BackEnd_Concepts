package com.devst.a10room

import androidx.lifecycle.LiveData

//talks with external db-->dao chaiye                external api ka bhi instance
class QuoteRepository(val quoteDao: QuoteDao) {



    fun getQuotes():LiveData<List<Quote> >{
        return quoteDao.getQuotes()
    }

    //suspend islye coz dao ka functon suspend the wo wo overlall suspend enviornment se hi call hona chahiye
    suspend fun insertQuote(quote: Quote){
        quoteDao.insertQuote(quote)
    }
}