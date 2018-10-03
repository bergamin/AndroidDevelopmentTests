package com.bergamin.roomexample.ui.fragment

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.text.InputType
import android.view.*
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ListView
import com.bergamin.roomexample.R
import com.bergamin.roomexample.database.DatabaseGenerator
import com.bergamin.roomexample.database.converter.DateConverter
import com.bergamin.roomexample.database.dao.ExamDAO
import com.bergamin.roomexample.delegate.ExamsDelegate
import com.bergamin.roomexample.model.Exam

class ExamsListFragment : Fragment() {

    private val delegate: ExamsDelegate by lazy { activity as ExamsDelegate }
    private lateinit var fab: FloatingActionButton
    private lateinit var list: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.list_exams_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.exams_list_menu_calendar -> {
                val context = context!!
                val fieldStart = EditText(context)
                val fieldEnd = EditText(context)
                val layout = LinearLayout(context)

                fieldStart.hint = getString(R.string.menu_exams_list_start)
                fieldStart.inputType = InputType.TYPE_DATETIME_VARIATION_DATE

                fieldEnd.hint = getString(R.string.menu_exams_list_end)
                fieldEnd.inputType = InputType.TYPE_DATETIME_VARIATION_DATE

                layout.orientation = LinearLayout.VERTICAL
                layout.addView(fieldStart)
                layout.addView(fieldEnd)

                AlertDialog.Builder(context)
                        .setView(layout)
                        .setMessage(R.string.menu_exams_list_message)
                        .setPositiveButton(R.string.menu_exams_list_search) { _, _ ->
                            val startDate = DateConverter.toCalendar(fieldStart)
                            val endDate = DateConverter.toCalendar(fieldEnd)
                            val dao = getExamDAO()
                            val exams = dao.getByPeriod(startDate, endDate)
                            list.adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, exams)
                        }
                        .setNegativeButton(R.string.menu_exams_list_cancel, null)
                        .show()
            }
        }
        return true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        configList(view)
        configFab(view)

        return view
    }

    private fun configList(view: View) {
        val dao = getExamDAO()
        val exams = dao.getAll()
        val adapter = ArrayAdapter(context!!, android.R.layout.simple_list_item_1, exams)

        list = view.findViewById(R.id.fragment_list)
        list.adapter = adapter

        list.setOnItemClickListener { _, _, position, _ ->
            val exam: Exam = list.getItemAtPosition(position) as Exam
            delegate.selectExam(exam)
        }
        list.setOnItemLongClickListener { _, _, position, _ ->
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

    private fun getExamDAO(): ExamDAO {
        val databaseGenerator = DatabaseGenerator()
        val database = databaseGenerator.generate(context!!)
        return database.getExamDao()
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
