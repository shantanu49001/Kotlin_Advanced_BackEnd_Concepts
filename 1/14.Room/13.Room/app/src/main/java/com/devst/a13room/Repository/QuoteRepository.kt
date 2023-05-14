package com.devst.a13room.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devst.a13room.Api.QuoteService
import com.devst.a13room.Db.QuoteDb
import com.devst.a13room.Models.QuoteList
import com.devst.a13room.NetworkUtil


class QuoteRepository(private val quoteService: QuoteService, private val quoteDb: QuoteDb, private val applicationContext: Context) {

    private val quoteLiveData = MutableLiveData<QuoteList>()
    val quotes: LiveData<QuoteList>
        get() = quoteLiveData
    suspend fun getQuote(page: Int) {
        if (NetworkUtil.isInternet(applicationContext)) {
            val result = quoteService.getQuotes(page)
            if (result?.body() != null) {
                quoteDb.quoteDao()
                    .addqUOTES(result.body()!!.results)
                quoteLiveData.postValue(result.body())

            } else {
                val quotes = quoteDb.quoteDao().getQuotes()
                val quoteList = QuoteList(1, 1, 1, quotes, 1, 1)
                quoteLiveData.postValue(quoteList)
            }
        }
    }
    suspend fun getQuotesBg() {
        val randomNumber = (Math.random() * 10).toInt()
        val result = quoteService.getQuotes(randomNumber)
        if (result?.body() != null) {
            quoteDb.quoteDao().addqUOTES(result.body()!!.results) } } }