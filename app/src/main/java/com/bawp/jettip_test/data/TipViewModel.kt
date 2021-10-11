package com.bawp.jettip_test.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.ViewModel

class TipViewModel: ViewModel() {

    /*
     Maybe not worth it for this example!
     */
    private val splitByState: MutableState<Int> = mutableStateOf(0)
    private val tipAmountState: MutableState<Double> = mutableStateOf(0.0)
    private val totalPerPersonState: MutableState<Int> = mutableStateOf(0)
    private val totalBillState: MutableState<String> =  mutableStateOf("")

    fun getSplitBy(): Int {
        return splitByState.value
    }
    fun getTipAmount(): Double {
        return tipAmountState.value
    }
    fun getTotalPerPerson(): Int {
        return totalPerPersonState.value
    }
    fun getTotaBill(): String {
        return totalBillState.value
    }









}