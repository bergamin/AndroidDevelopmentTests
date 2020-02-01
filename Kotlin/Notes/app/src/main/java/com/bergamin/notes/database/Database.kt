package com.bergamin.notes.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bergamin.notes.database.dao.NoteDAO
import com.bergamin.notes.model.Note

@Database(version = 1, entities = [Note::class])
//@TypeConverters(Converter::class, ...)
abstract class Database: RoomDatabase() {
    abstract fun getNoteDAO(): NoteDAO
}