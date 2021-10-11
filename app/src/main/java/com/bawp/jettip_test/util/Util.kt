package com.bawp.jettip_test.util

fun calculateTotalTip(totalBill: Double, tipPercent: Int): Double {

    return if (totalBill > 1 && totalBill.toString().isNotEmpty()) (totalBill * tipPercent) / 100 else 0.0
}
fun calculateTotalPerPerson(totalBill: Double, splitBy: Int, tipPercent: Int): Double {
    val bill = calculateTotalTip(totalBill, tipPercent = tipPercent) + totalBill
    return (bill/splitBy)
}