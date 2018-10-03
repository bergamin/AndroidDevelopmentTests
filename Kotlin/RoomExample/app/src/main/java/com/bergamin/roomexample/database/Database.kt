package com.bergamin.roomexample.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.bergamin.roomexample.database.converter.DateConverter
import com.bergamin.roomexample.database.dao.ExamDAO
import com.bergamin.roomexample.database.dao.StudentDAO
import com.bergamin.roomexample.model.Exam
import com.bergamin.roomexample.model.Student

@Database(version = 3, entities = [
    Student::class,
    Exam::class])
@TypeConverters(DateConverter::class)
abstract class Database : RoomDatabase() {
    abstract fun getStudentDao(): StudentDAO
    abstract fun getExamDao(): ExamDAO
}
