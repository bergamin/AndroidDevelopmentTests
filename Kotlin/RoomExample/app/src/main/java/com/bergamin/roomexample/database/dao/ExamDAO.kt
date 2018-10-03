package com.bergamin.roomexample.database.dao

import android.arch.persistence.room.*
import com.bergamin.roomexample.model.Exam
import java.util.*

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

    @Query("SELECT * FROM Exam WHERE date BETWEEN :start AND :end")
    fun getByPeriod(start: Calendar, end: Calendar): List<Exam>
}
