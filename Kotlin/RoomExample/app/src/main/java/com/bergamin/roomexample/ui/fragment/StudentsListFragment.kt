package com.bergamin.roomexample.ui.fragment

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.bergamin.roomexample.R
import com.bergamin.roomexample.database.DatabaseGenerator
import com.bergamin.roomexample.delegate.StudentsDelegate
import com.bergamin.roomexample.model.Student

class StudentsListFragment : Fragment() {

    private val delegate: StudentsDelegate by lazy { activity as StudentsDelegate }
    private lateinit var fab: FloatingActionButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        configList(view)
        configFAB(view)

        return view
    }

    private fun configList(view: View) {
        val databaseGenerator = DatabaseGenerator()
        val database = databaseGenerator.generate(context!!)
        val dao = database.getStudentDao()

        val list: ListView = view.findViewById(R.id.fragment_list)
        val students = dao.getAll()
        val adapter = ArrayAdapter(context!!, android.R.layout.simple_list_item_1, students)

        list.adapter = adapter
        list.setOnItemClickListener { _, _, position, _ ->
            val student: Student = list.getItemAtPosition(position) as Student
            delegate.selectStudent(student)
        }
        list.setOnItemLongClickListener { _, _, position, _ ->
            val student: Student = list.getItemAtPosition(position) as Student

            Snackbar.make(fab, "${getString(R.string.form_student_question_remove_student)} ${student.name}?", Snackbar.LENGTH_LONG)
                    .setAction(R.string.form_student_yes) {
                        dao.delete(student)
                        adapter.remove(student)
                    }
                    .show()

            true
        }
    }

    private fun configFAB(view: View) {
        fab = view.findViewById(R.id.fragment_list_fab)
        fab.setOnClickListener {
            delegate.clickFAB()
        }
    }

    override fun onResume() {
        super.onResume()
        delegate.setActivityTitle(getString(R.string.form_list_title))
    }
}
