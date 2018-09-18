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
class ViewAbstract(context: Context
                   , private val view: View
                   , private val transactions: List<Transaction>) {

    private val revenueColour = ContextCompat.getColor(context, R.color.revenue)
    private val expenseColour = ContextCompat.getColor(context, R.color.expense)

    fun updateTotals(){
        val revenue = sumByType(Type.REVENUE)
        val expense = sumByType(Type.EXPENSE)
        val total = revenue - expense

        with(view.card_revenue_abstract){
            text = revenue.efFormatCurrency()
            setTextColor(revenueColour)
        }
        with(view.card_expense_abstract){
            text = expense.efFormatCurrency()
            setTextColor(expenseColour)
        }
        with(view.card_total_abstract){
            text = total.efFormatCurrency()
            setTextColor(colourByValue(total))
        }
    }

    private fun colourByValue(value: BigDecimal): Int{
        if(value < BigDecimal.ZERO) {
            return expenseColour
        }
        return revenueColour
    }

    private fun sumByType(type: Type): BigDecimal{
        val sum = transactions
                .filter { it.type == type }
                .sumByDouble { it.value.toDouble() }
        return BigDecimal(sum)
    }
}
