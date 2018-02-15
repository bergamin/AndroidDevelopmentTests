package com.bergamin.finances.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bergamin.finances.R
import com.bergamin.finances.model.Transaction
import kotlinx.android.synthetic.main.transaction_item.view.*

/**
 * Created by Guilherme Taffarel Bergamin on 12/02/2018.
 */
class TransactionsListAdapter(transactions: List<Transaction>,
                              context: Context) : BaseAdapter() {

    private val transactions = transactions
    private val context = context

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val transactionItem = LayoutInflater.from(context).inflate(R.layout.transaction_item, parent, false)
        val transaction = transactions[position]

        transactionItem.transaction_value.text = transaction.value.toString()

        return transactionItem
    }

    override fun getItem(position: Int): Transaction {
        return transactions[position]
    }

    override fun getItemId(id: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return transactions.size
    }
}