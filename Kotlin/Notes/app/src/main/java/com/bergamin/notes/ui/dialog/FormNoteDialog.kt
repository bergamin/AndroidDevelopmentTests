package com.bergamin.notes.ui.dialog

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bergamin.notes.R
import com.bergamin.notes.model.Note
import kotlinx.android.synthetic.main.form_note.view.*

class FormNoteDialog(private val viewGroup: ViewGroup,
                     private val context: Context) {

    private val dialog = createLayout()
    private val title = dialog.form_note_title
    private val text = dialog.form_note_text

    private val positiveButtonTitle: Int = 0

    fun show(delegate: (note: Note) -> Boolean) {
        val alertDialog = AlertDialog.Builder(context)
            .setView(dialog)
            .setPositiveButton(context.getString(R.string.save), null)
            .setNegativeButton(context.getString(R.string.cancel), null)
            .create()

        alertDialog.setOnShowListener {
            val positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
            positiveButton.setOnClickListener {
                val note = Note(
                    title = title.text.toString(),
                    text = text.text.toString()
                )
                if(delegate(note)) {
                    alertDialog.dismiss()
                }
            }
        }
        alertDialog.show()
    }

    private fun createLayout(): View {
        return LayoutInflater
            .from(context)
            .inflate(R.layout.form_note, viewGroup, false)
    }
}