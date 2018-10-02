package com.bergamin.finances.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable
import java.math.BigDecimal
import java.util.*

@Entity
class Transaction(var value: BigDecimal
                 ,var category: String = "Undefined"
                 ,var date: Calendar = Calendar.getInstance()
                 ,var type: Type) : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
