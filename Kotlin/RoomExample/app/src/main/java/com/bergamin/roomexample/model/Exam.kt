package com.bergamin.roomexample.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity
class Exam : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var subject: String = ""

    override fun toString(): String {
        return subject
    }
}
