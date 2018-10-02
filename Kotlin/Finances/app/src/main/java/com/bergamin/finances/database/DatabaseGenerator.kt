package com.bergamin.finances.database

import android.arch.persistence.room.Room
import android.content.Context

class DatabaseGenerator {
    fun generate(context: Context): Database {
        return Room.databaseBuilder(context, Database::class.java, "FinancesDB")
                .allowMainThreadQueries()
//                .addMigrations()
                .build()
    }
}
