package com.bergamin.roomexample.database

import android.arch.persistence.room.Room
import android.content.Context

class DatabaseGenerator {

    fun generate(context: Context): Database {
        val database = Room.databaseBuilder(context, Database::class.java, "RoomExampleDatabase")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()

        return database
    }
}
