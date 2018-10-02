package com.bergamin.finances.database.dao

import android.arch.persistence.room.*
import com.bergamin.finances.model.Transaction

@Dao
interface TransactionDAO {
    @Insert
    fun insert(transaction: Transaction)
    @Update
    fun update(transaction: Transaction)
    @Delete
    fun delete(transaction: Transaction)
    @Query("SELECT * FROM `Transaction` ORDER BY Date DESC")
    fun getAll(): List<Transaction>
}
