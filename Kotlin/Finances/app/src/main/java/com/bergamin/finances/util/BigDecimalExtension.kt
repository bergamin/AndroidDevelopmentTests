package com.bergamin.finances.util

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.*

/**
 * Created by Guilherme Taffarel Bergamin on 16/02/2018.
 */

fun BigDecimal.efFormatCurrency(): String {
    val currencyFormat = DecimalFormat.getCurrencyInstance()
    return currencyFormat.format(this)
}

fun BigDecimal.efEqualsIgnoreScale(other: BigDecimal): Boolean {
    return this.unscaledValue() == other.unscaledValue()
}
