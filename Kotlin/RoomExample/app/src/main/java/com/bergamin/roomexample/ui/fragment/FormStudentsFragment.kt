package com.bergamin.roomexample.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.bergamin.roomexample.R
import com.bergamin.roomexample.database.DatabaseGenerator
import com.bergamin.roomexample.delegate.StudentsDelegate
import com.bergamin.roomexample.model.Student

class FormStudentsFragment : Fragment() {

    var student = Student()
    val delegate: StudentsDelegate by lazy { activity as StudentsDelegate }
    lateinit var nameField: EditText
    lateinit var emailField: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_form_students, container, false)

        nameField = view.findViewById(R.id.form_students_name)
        emailField = view.findViewById(R.id.form_students_email)

        configAddButton(view)
        loadStudent()

        return view
    }

    private fun configAddButton(view: View) {
        val add: Button = view.findViewById(R.id.form_students_add)
        add.setOnClickListener {
            update()
            persistStudent()
            delegate.goBack()
        }
    }

    private fun persistStudent() {
        val dbGenerator = DatabaseGenerator()
        val dao = dbGenerator.generate(context!!).getStudentDao()

        if (student.id == 0L) {
            dao.insert(student)
        } else {
            dao.update(student)
        }
    }

    private fun loadStudent() {
        val arguments = arguments

        if (arguments != null) {
            student = arguments.getSerializable("student") as Student
            nameField.setText(student.name)
            emailField.setText(student.email)
        }
    }

    private fun update() {
        student.name = nameField.text.toString()
        student.email = emailField.text.toString()
    }

    override fun onResume() {
        super.onResume()
        delegate.setActivityTitle(getString(R.string.form_student_title))
    }
}
