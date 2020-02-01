package com.bergamin.notes.database

import android.content.Context
import androidx.room.Room

class DatabaseGenerator {
    fun generate(context: Context): Database {
        return Room.databaseBuilder(context, Database::class.java, "NotesDB")
            .allowMainThreadQueries()
//            .addMigrations()
            .build()
    }
}