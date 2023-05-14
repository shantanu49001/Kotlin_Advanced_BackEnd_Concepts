package com.devst.a13room.Vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devst.a13room.Models.QuoteList
import com.devst.a13room.Repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//nwo this vm takes dat from the souve=repo
class MainVm(private val repository: QuoteRepository):ViewModel() {

    init {  //data listner hai khud se banta ha we don't crate it
        viewModelScope.launch(Dispatchers.IO) {
            repository.getQuote(1)  //repo apne private list me add kr dega

        }
    }
    val quotes:LiveData<QuoteList> //repo ke ass ek puvlic list thi jo ki private list ke contents ko copy kr rhi hai
    get() = repository.quotes  //we are acessing tha public list
}