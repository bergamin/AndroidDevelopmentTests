package com.bergamin.roomexample.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity
class Student : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var name: String = ""
    var email: String = ""

    override fun toString(): String {
        return "$id - $name"
    }
}
