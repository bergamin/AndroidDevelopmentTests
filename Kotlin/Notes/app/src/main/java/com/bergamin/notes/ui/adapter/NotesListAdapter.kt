package com.bergamin.notes.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bergamin.notes.R
import com.bergamin.notes.model.Note
import kotlinx.android.synthetic.main.note_list_item.view.*

class NotesListAdapter(private val notes: List<Note>,
                       private val context: Context) : BaseAdapter() {

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val noteItem = LayoutInflater.from(context).inflate(R.layout.note_list_item, parent, false)
        val note = notes[position]

        noteItem.note_list_title.text = note.title
        noteItem.note_list_text.text = note.text

        return noteItem
    }

    override fun getItem(position: Int) = notes[position]
    override fun getItemId(id: Int): Long = 0
    override fun getCount(): Int = notes.size
}