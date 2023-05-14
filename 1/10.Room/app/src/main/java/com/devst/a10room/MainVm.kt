package com.devst.a10room

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


//interacts wotj te repo        factory also needed coz it is param vm
class MainVm(private val repository: QuoteRepository):ViewModel() {

    //define functions acessing the repo
    fun getQuotes():LiveData<List<Quote>>{
        return repository.getQuotes()
    }

    //params bhi yahi se pass krenge till the end to sql quesry
    fun insertQuote(quote: Quote){


        //jab tak vm actve -->non main thread condtion
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertQuote(quote)

        }
    }


    //now the vm gets called by the activity
    //sequential calling of classes is observed that manage diffeent tasks

}