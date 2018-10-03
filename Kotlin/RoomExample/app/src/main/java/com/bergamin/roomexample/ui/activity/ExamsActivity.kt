package com.bergamin.roomexample.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.bergamin.roomexample.R
import com.bergamin.roomexample.delegate.ExamsDelegate
import com.bergamin.roomexample.model.Exam
import com.bergamin.roomexample.ui.fragment.ExamsListFragment
import com.bergamin.roomexample.ui.fragment.FormExamsFragment

class ExamsActivity : AppCompatActivity(), ExamsDelegate {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exams)

        show(ExamsListFragment(), false)
    }

    private fun show(fragment: Fragment, stack: Boolean) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()

        transaction.replace(R.id.exams_frame, fragment)
        if (stack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

    override fun clickFAB() {
        show(FormExamsFragment(), true)
    }

    override fun goBack() {
        onBackPressed()
    }

    override fun setActivityTitle(title: String) {
        setTitle(title)
    }

    override fun selectExam(exam: Exam) {
        val form = FormExamsFragment()
        val arguments = Bundle()

        arguments.putSerializable("exam", exam)
        form.arguments = arguments
        show(form, true)
    }
}
