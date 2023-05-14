package com.lukaslechner.coroutineusecasesonandroid.usecases.flow.Cancellation

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext

suspend fun main() {
    val scope = CoroutineScope(EmptyCoroutineContext)
    scope.launch {
        intFlow()
            .onCompletion { Throwable ->
                if (Throwable is CancellationException) {
                    println("Flow got cancelled")
                }
            }
            .collect {
                println("Collected $it")
                if (it==2){
                    cancel()
                }
            }
    }.join()   //collecting cr ke liye wait
}

private fun intFlow() = flow {
    emit(1)
    emit(2)
    emit(3)  //emit alwyas checks frst if the flow is still active
}
