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
import com.bergamin.roomexample.delegate.ExamsDelegate
import com.bergamin.roomexample.delegate.StudentsDelegate
import com.bergamin.roomexample.model.Exam

class ExamsListFragment : Fragment() {

    val delegate: ExamsDelegate by lazy { activity as ExamsDelegate }
    private lateinit var fab: FloatingActionButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        configList(view)
        configFab(view)

        return view
    }

    private fun configList(view: View) {
        val databaseGenerator = DatabaseGenerator()
        val database = databaseGenerator.generate(context!!)
        val dao = database.getExamDao()

        val list: ListView = view.findViewById(R.id.fragment_list)
        val exams = dao.getAll()
        val adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, exams)

        list.adapter = adapter
        list.setOnItemClickListener { _, _, position, _ ->
            val exam: Exam = list.getItemAtPosition(position) as Exam
            delegate.selectExam(exam)
        }
        list.setOnItemLongClickListener { _, _, position, id ->
            val exam: Exam = list.getItemAtPosition(position) as Exam

            Snackbar.make(fab, R.string.form_exam_question_remove_exam, Snackbar.LENGTH_LONG)
                    .setAction(R.string.form_exam_yes) {
                        dao.delete(exam)
                        adapter.remove(exam)
                    }
                    .show()

            true
        }
    }

    private fun configFab(view: View) {
        fab = view.findViewById(R.id.fragment_list_fab)
        fab.setOnClickListener {
            delegate.clickFAB()
        }
    }

    override fun onResume() {
        super.onResume()
        delegate.setActivityTitle(getString(R.string.form_list_exams_title))
    }
}
