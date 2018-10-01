package com.bergamin.roomexample.database.dao

import android.arch.persistence.room.*
import com.bergamin.roomexample.model.Exam

@Dao
interface ExamDAO {

    @Insert
    fun insert(exam: Exam)

    @Update
    fun update(exam: Exam)

    @Delete
    fun delete(exam: Exam)

    @Query("SELECT * FROM Exam")
    fun getAll(): List<Exam>
}
