package com.bergamin.notes.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bergamin.notes.R
import com.bergamin.notes.database.DatabaseGenerator
import com.bergamin.notes.database.dao.NoteDAO
import com.bergamin.notes.model.Note
import com.bergamin.notes.ui.adapter.NotesListAdapter
import com.bergamin.notes.ui.dialog.FormNoteDialog
import kotlinx.android.synthetic.main.activity_notes_list.*

class NotesListActivity: AppCompatActivity() {

    private lateinit var dao: NoteDAO
    private lateinit var notes: List<Note>
    private val activityView by lazy { window.decorView }
    private val activityViewGroup by lazy { activityView as ViewGroup }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_list)

        dao = DatabaseGenerator().generate(this).getNoteDAO()

        updateList()
        notes_add.setOnClickListener {
            callNoteDialog()
        }
    }

    private fun updateList() {
        notes = dao.getAll()

        with(notes_listview) {
            adapter = NotesListAdapter(notes, this@NotesListActivity)
            setOnItemClickListener { _, _, position, _ ->
                callEditDialog(notes[position])
            }
            setOnCreateContextMenuListener { menu, _, _ ->
                menu.add(Menu.NONE, 1, 1, context.getString(R.string.delete))
                menu.add(Menu.NONE, 2, 2, context.getString(R.string.edit))
            }
        }
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        val menuID = item?.itemId
        val adapterMenuInfo = item?.menuInfo as AdapterView.AdapterContextMenuInfo
        val position = adapterMenuInfo.position

        when(menuID) {
            1 -> delete(notes[position])
            2 -> callEditDialog(notes[position])
        }
        return super.onContextItemSelected(item)
    }

    private fun delete(note: Note) {
        dao.delete(note)
        updateList()
    }

    private fun callNoteDialog() {
        FormNoteDialog(activityViewGroup, this)
            .show {
                add(it)
            }
    }

    private fun callEditDialog(note: Note) {
//        FormNoteDialog(activityViewGroup, this)
//            .show(note) {
//                it.id = note.id
//                update(it)
//            }
    }

    private fun add(note: Note): Boolean {
        if(isValid(note, true)) {
            dao.insert(note)
            updateList()
            return true
        }
        return false
    }

    private fun update(note: Note): Boolean {
        if(isValid(note, true)) {
            dao.update(note)
            updateList()
            return true
        }
        return false
    }

    private fun isValid(note: Note, showMessage: Boolean = false): Boolean {
        // Using a list in case more validations are needed in the future
        val errorMessages = mutableListOf<String>()

        if(note.title.isBlank() && note.text.isBlank())
            errorMessages.add(getString(R.string.error_001_note_is_empty))

        if(errorMessages.size > 0) {
            if(showMessage) {
                var fullMessage = ""
                if(errorMessages.size == 1) {
                    fullMessage = errorMessages[0]
                } else {
                    for(message in errorMessages) {
                        fullMessage += "- $message\n"
                    }
                }
                Toast.makeText(this@NotesListActivity, fullMessage.trim(), Toast.LENGTH_LONG).show()
            }
            return false
        }
        return true
    }
}
