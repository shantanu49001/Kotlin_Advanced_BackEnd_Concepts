package com.devst.a13room.Api

import com.devst.a13room.Models.QuoteList
import com.google.gson.Gson
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// https://quotable.io/quotes?page=1
interface QuoteService {
    @GET("/quotes")  //functio acessd directly by object of interface
    suspend fun getQuotes(@Query("page")page:Int):Response<QuoteList>
//

}

object RetrofitHelper{
    private const val BASE_URL="https://quotable.io/"

fun getInstance():Retrofit{
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
}