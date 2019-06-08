package com.bergamin.notes.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class Note(var text: String): Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}