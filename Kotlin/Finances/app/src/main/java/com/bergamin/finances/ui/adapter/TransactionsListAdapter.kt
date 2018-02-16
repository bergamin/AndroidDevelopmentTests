package com.bergamin.finances.ui.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bergamin.finances.R
import com.bergamin.finances.extension.efFormatCurrency
import com.bergamin.finances.extension.efFormat
import com.bergamin.finances.model.Transaction
import com.bergamin.finances.model.Type
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

        when(transaction.type){
            Type.REVENUE -> {
                transactionItem.transaction_value.setTextColor(ContextCompat.getColor(context,R.color.revenue))
                transactionItem.transaction_icon.setBackgroundResource(R.drawable.ic_transaction_item_revenue)
            }
            Type.EXPENSE -> {
                transactionItem.transaction_value.setTextColor(ContextCompat.getColor(context,R.color.expense))
                transactionItem.transaction_icon.setBackgroundResource(R.drawable.ic_transaction_item_expense)
            }
        }

        transactionItem.transaction_value.text = transaction.value.efFormatCurrency()
        transactionItem.transaction_category.text = transaction.category
        transactionItem.transaction_date.text = transaction.date.efFormat(context)

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