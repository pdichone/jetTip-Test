package com.bawp.jettip_test.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel

class CounterViewModel: ViewModel() {
   private val counter: MutableState<Int> = mutableStateOf(0)

    fun increaseCounter() {
        counter.value = counter.value + 1
    }
    fun decreaseCounter() {
        if (counter.value > 0)
           counter.value = counter.value - 1
    }
    fun getCounter(): Int {
       return counter.value
    }


}