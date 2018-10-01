package com.bergamin.roomexample.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity
class Exam : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var subject: String = ""
    var date: Calendar = Calendar.getInstance()

    override fun toString(): String {
        return subject
    }
}
