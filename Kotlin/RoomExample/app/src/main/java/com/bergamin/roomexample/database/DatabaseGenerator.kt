package com.bergamin.roomexample.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.migration.Migration
import android.content.Context

class DatabaseGenerator {
    fun generate(context: Context): Database {
        return Room.databaseBuilder(context, Database::class.java, "RoomExampleDatabase")
                .allowMainThreadQueries()
                .addMigrations(
                        Migration0102(),
                        Migration0203())
                .build()
    }
}

class Migration0203 : Migration(2,3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE Exam ADD COLUMN date INTEGER;")
    }
}

class Migration0102 : Migration(1,2) {
    override fun migrate(database: SupportSQLiteDatabase) {}
}
