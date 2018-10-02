package com.bergamin.finances.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.bergamin.finances.database.converter.DateConverter
import com.bergamin.finances.database.converter.DecimalConverter
import com.bergamin.finances.database.converter.TypeConverter
import com.bergamin.finances.database.dao.TransactionDAO
import com.bergamin.finances.model.Transaction

@Database(version = 1, entities = [Transaction::class])
@TypeConverters(
        DecimalConverter::class,
        DateConverter::class,
        TypeConverter::class)
abstract class Database : RoomDatabase() {
    abstract fun getTransactionDAO(): TransactionDAO
}
