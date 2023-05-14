package com.lukaslechner.coroutineusecasesonandroid.usecases.flow.usecase1

import com.lukaslechner.coroutineusecasesonandroid.usecases.flow.mock.FlowMockApi
import com.lukaslechner.coroutineusecasesonandroid.usecases.flow.mock.Stock
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import retrofit2.HttpException
import timber.log.Timber

interface StockPriceDataSource {
    val latestStockList: Flow<List<Stock>>
}


class NetworkStockPriceDataSource(mockApi: FlowMockApi) : StockPriceDataSource {
    //what should we use flowOn [builder  ] or asFlow(extesion funcion) or flow{}-->builder?
    //since we don't know size of return tupe
    override val latestStockList: Flow<List<Stock>> = flow {
        while (true) {//periodivally call krne ke liye
            Timber.tag("Flow").d("Fetching current stock prices")
            val currentStockList = mockApi.getCurrentStockPrices()
            emit(currentStockList)//repetetive emits asynchr data stream
            delay(5_000)
        }
    }.retry {
        Timber.tag("Flow").d("Enter retry ")
        delay(5_000)
        val should_retyr = it is HttpException
        if (should_retyr) {
            delay(500)
        }
        should_retyr
    }
}