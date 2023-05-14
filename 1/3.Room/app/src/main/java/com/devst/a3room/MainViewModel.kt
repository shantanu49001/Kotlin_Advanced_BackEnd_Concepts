package com.devst.a3room

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context: Context) : ViewModel() {
    private val quoteList: Array<Quote> = emptyArray() //data that will be shown
    private var index = 1

    init {    //as sson as this class called/formed-->jo bhi index hoga us time
        loadQuotes()

    }

    private fun loadQuotes(): Array<Quote> {

        //param vm
        ///main vm req application context to acess assets
        val inputStream = context.assets.open("quotes.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val json = String(buffer, Charsets.US_ASCII)
        val gson = Gson()
        return gson.fromJson(json, Array<Quote>::class.java)

    }

    //arrray calculted in vm alreydy

    //we can acess these by functions
    fun getindex() = quoteList[index]    //function retuns this value
    fun nextQuots() = quoteList[index++]
    fun prevQuots() = quoteList[--index]

}