package com.bergamin.notes.database.dao

import androidx.room.*
import com.bergamin.notes.model.Note

@Dao
interface NoteDAO {
    @Insert
    fun insert(note: Note)
    @Update
    fun update(note: Note)
    @Delete
    fun delete(note: Note)
    @Query("SELECT * FROM Note ORDER BY id DESC")
    fun getAll(): List<Note>
}
