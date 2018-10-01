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
import com.bergamin.roomexample.database.converter.DateConverter
import com.bergamin.roomexample.delegate.ExamsDelegate
import com.bergamin.roomexample.model.Exam

class FormExamsFragment : Fragment() {

    private val delegate: ExamsDelegate by lazy { activity as ExamsDelegate }
    private var exam = Exam()

    private lateinit var addButton: Button
    private lateinit var subjectField: EditText
    private lateinit var dateField: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_form_exams, container, false)

        getFieldsFromView(view)
        configAddButton()
        loadExam()

        return view
    }

    private fun getFieldsFromView(view: View) {
        addButton = view.findViewById(R.id.form_exam_add)
        subjectField = view.findViewById(R.id.form_exam_subject)
        dateField = view.findViewById(R.id.form_exam_date)
    }

    private fun configAddButton() {
        addButton.setOnClickListener {
            update()
            persistExam()
            delegate.goBack()
        }
    }

    private fun persistExam() {
        val dbGenerator = DatabaseGenerator()
        val dao = dbGenerator.generate(context!!).getExamDao()

        if (exam.id == 0L) {
            dao.insert(exam)
        } else {
            dao.update(exam)
        }
    }

    private fun loadExam() {
        val arguments = arguments

        if (arguments != null) {
            exam = arguments.getSerializable("exam") as Exam
            subjectField.setText(exam.subject)
            dateField.setText(DateConverter.toString(exam.date))
        }
    }

    private fun update() {
        exam.subject = subjectField.text.toString()
        exam.date = DateConverter.toCalendar(dateField.text.toString())
    }

    override fun onResume() {
        super.onResume()
        delegate.setActivityTitle(getString(R.string.form_exam_title))
    }
}
