package com.bergamin.finances.ui.activity

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import com.bergamin.finances.R
import com.bergamin.finances.model.Transaction
import com.bergamin.finances.model.Type
import com.bergamin.finances.ui.ViewAbstract
import com.bergamin.finances.ui.adapter.TransactionsListAdapter
import com.bergamin.finances.util.*
import kotlinx.android.synthetic.main.activity_transactions_list.*
import kotlinx.android.synthetic.main.form_transaction.view.*
import java.math.BigDecimal
import java.util.*

/**
 * Created by Guilherme Taffarel Bergamin on 12/01/2018.
 */
class TransactionsListActivity : AppCompatActivity() {

    private var transactions: MutableList<Transaction> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transactions_list)

        updateTotals()

        transactions_add_revenue.setOnClickListener {
            createTransactionDialog()
        }
    }

    private fun updateTotals() {
        ViewAbstract(this, window.decorView, transactions).updateTotals()
        transactions_listview.adapter = TransactionsListAdapter(transactions,this)
    }

    private fun add(transaction: Transaction) {
        if(isValid(transaction)) {
            transactions.add(transaction)
            updateTotals()
            transactions_add_menu.close(true)
        }
    }

    private fun isValid(transaction: Transaction): Boolean{
        val errorMessages = mutableListOf<String>()
        if(transaction.value == BigDecimal.ZERO){
            errorMessages.add(this@TransactionsListActivity.getString(R.string.required_value))
        }
        if(transaction.value < BigDecimal.ZERO){
            errorMessages.add(this@TransactionsListActivity.getString(R.string.negative_value))
        }

        if(errorMessages.size > 0) {
            var fullMessage = ""
            if(errorMessages.size == 1) {
                fullMessage = errorMessages[0]
            }else{
                for(message in errorMessages){
                    fullMessage += "- $message\n"
                }
            }
            Toast.makeText(this@TransactionsListActivity,fullMessage.trim(),Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }
}
