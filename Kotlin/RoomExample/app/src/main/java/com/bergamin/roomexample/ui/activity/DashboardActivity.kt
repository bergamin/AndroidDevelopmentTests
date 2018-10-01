package com.bergamin.roomexample.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.bergamin.roomexample.R

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        configAddStudent()
        configAddExam()
    }

    private fun configAddStudent() {
        val addStudent: Button = findViewById(R.id.dash_btn_student)
        addStudent.setOnClickListener {
            val intent = Intent(this@DashboardActivity, StudentsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun configAddExam() {
        val addExam: Button = findViewById(R.id.dash_btn_exams)
        addExam.setOnClickListener {
            val intent = Intent(this@DashboardActivity, ExamsActivity::class.java)
            startActivity(intent)
        }
    }
}
