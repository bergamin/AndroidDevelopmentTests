package com.bergamin.finances.extension

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.*

/**
 * Created by Guilherme Taffarel Bergamin on 16/02/2018.
 */

fun BigDecimal.efFormatCurrency(): String{
    val currencyFormat = DecimalFormat.getCurrencyInstance(Locale.getDefault())
    return currencyFormat.format(this)
}
