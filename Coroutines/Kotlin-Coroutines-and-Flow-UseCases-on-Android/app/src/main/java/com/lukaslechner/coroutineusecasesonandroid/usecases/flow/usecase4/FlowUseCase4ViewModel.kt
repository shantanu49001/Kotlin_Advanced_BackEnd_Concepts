package com.lukaslechner.coroutineusecasesonandroid.usecases.flow.usecase4

import androidx.lifecycle.viewModelScope
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import kotlinx.coroutines.flow.*
import timber.log.Timber

class FlowUseCase4ViewModel(stockPriceDataSource: StockPriceDataSource) : BaseViewModel<UiState>() {


    val currentStockPriceAsFlow: Flow<UiState> = stockPriceDataSource.latestStockList
        .map { stockList ->
            UiState.Success(stockList)
        }
        .onStart {
            // emit(emptyArray())
        }
        .onCompletion {
            Timber.tag("Flow").d("Flow Complted")
        }
        //no launciIn or asLive  needed  now  as  it  is  captured  as  it  is
        .shareIn(viewModelScope, SharingStarted.WhileSubscribed())   //needs a coroutine
}