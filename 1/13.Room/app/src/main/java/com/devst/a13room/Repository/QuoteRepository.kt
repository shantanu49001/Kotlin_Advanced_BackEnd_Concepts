package com.devst.a13room.Repository

import android.text.style.QuoteSpan
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devst.a13room.Api.QuoteService
import com.devst.a13room.Models.QuoteList

//dat asource methods add retrofit me it is the api building file that has a callback functiojn in room it is the dao
class QuoteRepository(private val quoteService: QuoteService) {
    //just as room all the end points

    private val quoteLiveData = MutableLiveData<QuoteList>()
    val quotes: LiveData<QuoteList>  //vm acesss this
        get() = quoteLiveData

    suspend fun getQuote(page: Int) { //vm will cal this
        val result = quoteService.getQuotes(page) //thid brings the data
        if (result?.body() != null) {
            quoteLiveData.postValue(result.body())  //adding the daat to alreaduy crared repo owned ist

            //repo private list me data add krtei rehti hai
            //quotes repo ki array ke equal hai
            //vm quotes ko aceess krke le jata hai


        }
    }

}