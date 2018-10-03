package com.bergamin.roomexample.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.bergamin.roomexample.R
import com.bergamin.roomexample.delegate.StudentsDelegate
import com.bergamin.roomexample.model.Student
import com.bergamin.roomexample.ui.fragment.FormStudentsFragment
import com.bergamin.roomexample.ui.fragment.StudentsListFragment

class StudentsActivity : AppCompatActivity(), StudentsDelegate {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students)

        show(StudentsListFragment(), false)
    }

    private fun show(fragment: Fragment, stack: Boolean) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()

        transaction.replace(R.id.students_frame, fragment)
        if (stack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

    override fun clickFAB() {
        show(FormStudentsFragment(), true)
    }

    override fun goBack() {
        onBackPressed()
    }

    override fun setActivityTitle(title: String) {
        setTitle(title)
    }

    override fun selectStudent(student: Student) {
        val form = FormStudentsFragment()
        val arguments = Bundle()

        arguments.putSerializable("student", student)
        form.arguments = arguments
        show(form, true)
    }
}
