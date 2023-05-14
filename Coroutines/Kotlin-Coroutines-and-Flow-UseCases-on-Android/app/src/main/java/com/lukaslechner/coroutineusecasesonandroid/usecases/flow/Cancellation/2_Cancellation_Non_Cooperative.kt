package com.lukaslechner.coroutineusecasesonandroid.usecases.flow.Cancellation

import com.lukaslechner.coroutineusecasesonandroid.usecases.flow.mock.fakeCurrentStockPrices
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import java.math.BigInteger
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
    println("Start Calculation")
    calculllateFact(10)
    println("End Calculation")

    emit(3)  //emit alwyas checks frst if the flow is still active
}

private fun calculllateFact(number: Int):BigInteger{
    var fatorial=BigInteger.ONE
    for (i in 1..number){
        fatorial=fatorial.multiply(BigInteger.valueOf(i.toLong()))
    }
    return fatorial
}