package com.bergamin.finances.ui.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bergamin.finances.R
import com.bergamin.finances.database.converter.DateConverter
import com.bergamin.finances.model.Transaction
import com.bergamin.finances.model.Type
import com.bergamin.finances.util.efFormatCurrency
import com.bergamin.finances.util.efLimitsIn
import kotlinx.android.synthetic.main.transaction_item.view.*

class TransactionsListAdapter(private val transactions: List<Transaction>,
                              private val context: Context) : BaseAdapter() {

    private val categoryLimit = 14

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val transactionItem = LayoutInflater.from(context).inflate(R.layout.transaction_item, parent, false)
        val transaction = transactions[position]

        val valueColour: Int
        val transactionIcon: Int

        when(transaction.type){
            Type.REVENUE -> {
                valueColour = ContextCompat.getColor(context,R.color.revenue)
                transactionIcon = R.drawable.ic_transaction_item_revenue
            }
            Type.EXPENSE -> {
                valueColour = ContextCompat.getColor(context,R.color.expense)
                transactionIcon = R.drawable.ic_transaction_item_expense
            }
        }

        transactionItem.transaction_icon.setBackgroundResource(transactionIcon)
        transactionItem.transaction_value.setTextColor(valueColour)
        transactionItem.transaction_value.text = transaction.value.efFormatCurrency()
        transactionItem.transaction_category.text = transaction.category.efLimitsIn(categoryLimit)
        transactionItem.transaction_date.text = DateConverter.toString(transaction.date)

        return transactionItem
    }

    override fun getItem(position: Int): Transaction = transactions[position]
    override fun getItemId(id: Int): Long = 0
    override fun getCount(): Int = transactions.size
}
