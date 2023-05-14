package com.devst.a13room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.devst.a13room.Repository.QuoteRepository
import com.devst.a13room.Vm.MainVm

//view odel ko repo pass krnei hai
class MainVmProviders(private val repository: QuoteRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainVm(repository) as T
    }
}