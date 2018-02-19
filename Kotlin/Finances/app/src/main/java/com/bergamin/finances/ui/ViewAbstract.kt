package com.bergamin.finances.ui

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View
import com.bergamin.finances.R
import com.bergamin.finances.util.efFormatCurrency
import com.bergamin.finances.model.Transaction
import com.bergamin.finances.model.Type
import kotlinx.android.synthetic.main.card_abstract.view.*
import java.math.BigDecimal

/**
 * Created by Guilherme Taffarel Bergamin on 19/02/2018.
 */
class ViewAbstract(private val context: Context
                  ,private val view: View) {
    fun calculateTotals(transactions: List<Transaction>){
        var total = BigDecimal.ZERO
        var revenue = BigDecimal.ZERO
        var expense = BigDecimal.ZERO

        for(transaction in transactions){
            when(transaction.type){
                Type.REVENUE -> {
                    total += transaction.value
                    revenue +=transaction.value
                }
                Type.EXPENSE -> {
                    total -= transaction.value
                    expense += transaction.value
                }
            }
        }
        view.card_revenue_abstract.text = revenue.efFormatCurrency()
        view.card_expense_abstract.text = expense.efFormatCurrency()
        view.card_total_abstract.text = total.efFormatCurrency()

        view.card_revenue_abstract.setTextColor(ContextCompat.getColor(context,R.color.revenue))
        view.card_expense_abstract.setTextColor(ContextCompat.getColor(context,R.color.expense))
        if(total < BigDecimal.ZERO) {
            view.card_total_abstract.setTextColor(ContextCompat.getColor(context,R.color.expense))
        }else{
            view.card_total_abstract.setTextColor(ContextCompat.getColor(context,R.color.revenue))
        }
    }
}
