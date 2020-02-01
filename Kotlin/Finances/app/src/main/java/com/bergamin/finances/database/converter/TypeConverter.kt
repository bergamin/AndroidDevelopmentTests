package com.bergamin.finances.database.converter

import android.arch.persistence.room.TypeConverter
import com.bergamin.finances.model.Type

class TypeConverter {
    companion object {

        @TypeConverter
        @JvmStatic
        fun toString(type: Type): String {
            return type.toString()
        }

        @TypeConverter
        @JvmStatic
        fun toType(text: String): Type {
            return Type.valueOf(text.toUpperCase())
        }
    }
}