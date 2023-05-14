package com.devst.a13room

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteWorker(private val context: Context,params:WorkerParameters):Worker(context,params) {
    override fun doWork(): Result {
        val repository=(context as QuoteApplication).quoteRepository
        CoroutineScope(Dispatchers.IO).launch {
            repository.getQuotesBg()
        }
        return Result.success()
    }

}