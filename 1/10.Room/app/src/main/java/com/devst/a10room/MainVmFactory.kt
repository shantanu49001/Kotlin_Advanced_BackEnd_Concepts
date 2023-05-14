package com.devst.a10room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//all the params viewmodel needs in evey of it' function are apssed from here
class MainVmFactory(private val repository: QuoteRepository):ViewModelProvider.Factory {
//delare the params that are not passed by activty from here
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainVm(repository) as T
    }
}