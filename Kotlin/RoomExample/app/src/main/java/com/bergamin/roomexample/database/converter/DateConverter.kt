package com.bergamin.roomexample.database.converter

import android.arch.persistence.room.TypeConverter
import android.widget.EditText
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateConverter {
    companion object {

        // TODO: check better ways to get a date format based on device locale because of 'Murika
        private const val FORMAT = "dd/MM/yyyy"
        private val formatter = SimpleDateFormat(FORMAT)

        @TypeConverter
        @JvmStatic
        fun toLong(date: Calendar): Long {
            return date.time.time
        }

        @TypeConverter
        @JvmStatic
        fun toCalendar(milliseconds: Long): Calendar {
            val calendar = Calendar.getInstance()
            calendar.time = Date(milliseconds)
            return calendar
        }

        fun toCalendar(date: String): Calendar {
            val calendar = Calendar.getInstance()
            try {
                val parsedDate = formatter.parse(date)
                calendar.time = parsedDate
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return calendar
        }

        fun toString(date: Calendar): String {
            return formatter.format(date.time)
        }

        fun toCalendar(editText: EditText): Calendar {
            return toCalendar(editText.text.toString())
        }
    }
}
