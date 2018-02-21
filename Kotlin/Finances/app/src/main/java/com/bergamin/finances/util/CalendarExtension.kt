package com.bergamin.finances.util

import android.content.Context
import com.bergamin.finances.R
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Guilherme Taffarel Bergamin on 16/02/2018.
 */

fun Calendar.efFormat(context: Context): String {
    val dateFormat = SimpleDateFormat(context.getString(R.string.date_format)) // TODO: change this automatically based on location
    return dateFormat.format(this.time)
}

fun Calendar.efDay() = this.get(Calendar.DAY_OF_MONTH)
fun Calendar.efMonth() = this.get(Calendar.MONTH)
fun Calendar.efYear() = this.get(Calendar.YEAR)