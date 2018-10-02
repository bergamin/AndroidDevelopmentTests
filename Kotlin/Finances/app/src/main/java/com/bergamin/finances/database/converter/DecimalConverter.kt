package com.bergamin.finances.database.converter

import android.arch.persistence.room.TypeConverter
import android.widget.EditText
import java.math.BigDecimal

class DecimalConverter {
    companion object {

        @TypeConverter
        @JvmStatic
        fun toDouble(bigDecimal: BigDecimal): Double {
            return bigDecimal.toDouble()
        }

        @TypeConverter
        @JvmStatic
        fun toBigDecimal(double: Double): BigDecimal {
            return BigDecimal(double)
        }

        fun toBigDecimal(editText: EditText): BigDecimal {
            return toBigDecimal(editText.text.toString())
        }

        fun toBigDecimal(text: String): BigDecimal {
            return try {
                BigDecimal(text)
            } catch (nfe: NumberFormatException) {
                BigDecimal.ZERO
            }
        }
    }
}