package com.bergamin.roomexample.database.dao

import android.arch.persistence.room.*
import com.bergamin.roomexample.model.Student

@Dao
interface StudentDAO {

    @Insert
    fun insert(student: Student)

    @Update
    fun update(student: Student)

    @Delete
    fun delete(student: Student)

    @Query("SELECT * FROM Student ORDER BY name")
    fun getAll(): List<Student>
}
