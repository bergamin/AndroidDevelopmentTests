package com.bergamin.finances.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bergamin.finances.R
import com.bergamin.finances.ui.ViewAbstract
import com.bergamin.finances.ui.adapter.TransactionsListAdapter
import com.bergamin.finances.util.createExampleData
import kotlinx.android.synthetic.main.activity_transactions_list.*

/**
 * Created by Guilherme Taffarel Bergamin on 12/01/2018.
 */
class TransactionsListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transactions_list)

        val transactions = createExampleData()

        ViewAbstract(this, window.decorView, transactions).updateTotals()
        transactions_listview.adapter = TransactionsListAdapter(transactions,this)
    }
}
