package com.devst.room12

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//all the tyes of end points
interface QuotesApi {
    //api and retuens by api
//https://quotable.io/quotes?page=1     base url till .io/    end point is quotes?  param are page=1
    //syspend is liye cos it is feavy task main thrwad pr nhi chalega
    @GET("/quotes")
    suspend fun getQuotes(@Query("page") page: Int): Response<QuoteList>


}

object RetrofitHelper {  //object to get ye api
    val BASE_URL = "https://quotable.io/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}