package com.bergamin.roomexample.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.bergamin.roomexample.database.dao.ExamDAO
import com.bergamin.roomexample.database.dao.StudentDAO
import com.bergamin.roomexample.model.Exam
import com.bergamin.roomexample.model.Student

@Database(entities = arrayOf(Student::class, Exam::class), version = 2)
abstract class Database : RoomDatabase() {
    abstract fun getStudentDao(): StudentDAO
    abstract fun getExamDao(): ExamDAO
}
