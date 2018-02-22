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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transactions_list)

        val today = Calendar.getInstance()

        val transactions = createExampleData()

        ViewAbstract(this, window.decorView, transactions).updateTotals()
        transactions_listview.adapter = TransactionsListAdapter(transactions,this)

        transactions_add_revenue.setOnClickListener {

            val addRevenueDialog = LayoutInflater.from(this)
                    .inflate(R.layout.form_transaction, window.decorView as ViewGroup, false)

            with(addRevenueDialog.form_transaction_date){
                setText(today.efFormat(this@TransactionsListActivity))
                setOnClickListener {
                    DatePickerDialog(this@TransactionsListActivity, DatePickerDialog.OnDateSetListener { view, year, month, day ->
                        val selectedDate = Calendar.getInstance()
                        selectedDate.set(year, month, day)
                        this.setText(selectedDate.efFormat(this@TransactionsListActivity))
                    }, today.efYear(), today.efMonth(), today.efDay())
                            .show()
                }
            }

            val adapter = ArrayAdapter.createFromResource(this, R.array.revenue_categories,android.R.layout.simple_spinner_dropdown_item)
            addRevenueDialog.form_transaction_category.adapter = adapter

            AlertDialog.Builder(this)
                    .setTitle(R.string.add_revenue)
                    .setView(addRevenueDialog)
                    .setPositiveButton(R.string.add, { dialogInterface, i ->
                        val value = BigDecimal(addRevenueDialog.form_transaction_value.text.toString())
                        val date = addRevenueDialog.form_transaction_date.text.toString().efParseCalendar(this)
                        val category = addRevenueDialog.form_transaction_category.selectedItem.toString()
                        val transaction = Transaction(
                                 type = Type.REVENUE
                                ,value = value
                                ,date = date
                                ,category = category)
                    })
                    .setNegativeButton(R.string.cancel, null)
                    .show()
        }
    }
}
