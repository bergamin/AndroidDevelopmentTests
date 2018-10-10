package com.bergamin.finances.util

import android.content.Context
import kotlinx.android.synthetic.main.form_transaction.view.*
import java.math.BigDecimal
import java.util.*

/**
 * Created by Guilherme Taffarel Bergamin on 17/02/2018.
 */

fun String.efLimitsIn(length: Int): String {
    if(this.length > length){
        return "${this.substring(0,length)}..." // TODO: make this variable and work well for different screen sizes
    }
    return this
}

fun String.efParseCalendar(context: Context): Calendar {
    val returnDate = Calendar.getInstance()
    returnDate.time = localeDateFormat(context).parse(this)
    return returnDate
}

fun String.efParseBigDecimal(): BigDecimal {
    return try {
        BigDecimal(this)
    } catch (nfe: NumberFormatException) {
        BigDecimal.ZERO
    }
}