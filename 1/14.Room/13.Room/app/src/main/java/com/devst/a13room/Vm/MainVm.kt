package com.devst.a13room.Vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devst.a13room.Models.QuoteList
import com.devst.a13room.Repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
class MainVm(private val repository: QuoteRepository):ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getQuote(1)

        }
    }
    val quotes:LiveData<QuoteList>
    get() = repository.quotes
}