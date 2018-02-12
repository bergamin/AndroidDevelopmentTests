package com.bergamin.finances.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import com.bergamin.finances.R
import com.bergamin.finances.ui.adapter.TransactionsListAdapter
import kotlinx.android.synthetic.main.activity_transactions_list.*

/**
 * Created by Guilherme Taffarel Bergamin on 12/01/2018.
 */
class TransactionsListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transactions_list)

        val transactions = listOf<String>()
        
        transactions_listview.adapter = TransactionsListAdapter(transactions,this)
    }
}