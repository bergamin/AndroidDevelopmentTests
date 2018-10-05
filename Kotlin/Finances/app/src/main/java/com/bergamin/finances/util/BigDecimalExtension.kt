package com.bergamin.finances.util

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.*

fun BigDecimal.efFormatCurrency(): String{
    val currencyFormat = DecimalFormat.getCurrencyInstance(Locale.getDefault())
    return currencyFormat.format(this)
}

fun BigDecimal.efEqualsIgnoreScale(other: BigDecimal): Boolean {
    return this.unscaledValue() == other.unscaledValue()
}
