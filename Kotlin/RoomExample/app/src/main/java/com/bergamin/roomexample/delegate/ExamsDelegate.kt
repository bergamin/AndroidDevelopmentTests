package com.bergamin.roomexample.delegate

import com.bergamin.roomexample.model.Exam

interface ExamsDelegate {
    fun clickFAB()
    fun goBack()
    fun setActivityTitle(title: String)
    fun selectExam(exam: Exam)
}
