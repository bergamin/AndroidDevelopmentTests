package com.bergamin.finances.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bergamin.finances.R
import com.bergamin.finances.model.Transaction
import com.bergamin.finances.ui.adapter.TransactionsListAdapter
import kotlinx.android.synthetic.main.activity_transactions_list.*
import java.util.*

/**
 * Created by Guilherme Taffarel Bergamin on 12/01/2018.
 */
class TransactionsListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transactions_list)

        val transactions = listOf(Transaction(20.5,"Food", Calendar.getInstance()),
                                  Transaction(100,"Savings", Calendar.getInstance()))

        transactions_listview.adapter = TransactionsListAdapter(transactions,this)
    }
}