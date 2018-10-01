package com.bergamin.roomexample.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.migration.Migration
import android.content.Context

class DatabaseGenerator {
    fun generate(context: Context): Database {
        return Room.databaseBuilder(context, Database::class.java, "RoomExampleDatabase")
                .allowMainThreadQueries()
                .addMigrations(Migration_2_3())
                .build()
    }
}

class Migration_2_3 : Migration(2,3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE Exam ADD COLUMN date INTEGER;")
    }
}
