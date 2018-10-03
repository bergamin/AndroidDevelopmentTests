package com.bergamin.roomexample.delegate

import com.bergamin.roomexample.model.Student

interface StudentsDelegate {
    fun clickFAB()
    fun goBack()
    fun setActivityTitle(title: String)
    fun selectStudent(student: Student)
}
