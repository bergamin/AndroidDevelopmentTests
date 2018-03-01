package com.bergamin.finances.ui.dialog

import android.app.DatePickerDialog
import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.bergamin.finances.R
import com.bergamin.finances.R.id.transactions_add_menu
import com.bergamin.finances.R.id.transactions_listview
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
 * Created by Guilherme Taffarel Bergamin on 01/03/2018.
 */
class TransactionDialog(private val viewGroup: ViewGroup,
                        private val context: Context) {

    private val dialog = createLayout()

    private fun create() {
        configDateField()
        configCategoryField()
        configForm()
    }

    private fun createLayout(): View {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.form_transaction, viewGroup, false)
    }

    private fun configDateField() {
        val today = Calendar.getInstance()

        with(dialog.form_transaction_date) {
            setText(today.efFormat(context))
            setOnClickListener {
                DatePickerDialog(context, DatePickerDialog.OnDateSetListener { _, year, month, day ->
                    val selectedDate = Calendar.getInstance()
                    selectedDate.set(year, month, day)
                    this.setText(selectedDate.efFormat(context))
                }, today.efYear(), today.efMonth(), today.efDay())
                        .show()
            }
        }
    }

    private fun configCategoryField() {
        val adapter = ArrayAdapter.createFromResource(context, R.array.revenue_categories, android.R.layout.simple_spinner_dropdown_item)
        dialog.form_transaction_category.adapter = adapter
    }

    private fun configForm() {
        AlertDialog.Builder(context)
                .setTitle(R.string.add_revenue)
                .setView(dialog)
                .setPositiveButton(R.string.add, { _, _ ->
                    val value = dialog.form_transaction_value.text.toString().efParseBigDecimal()
                    val date = dialog.form_transaction_date.text.toString().efParseCalendar(context)
                    val category = dialog.form_transaction_category.selectedItem.toString()

                    add(Transaction(
                            type = Type.REVENUE,
                            value = value,
                            date = date,
                            category = category))
                })
                .setNegativeButton(R.string.cancel, null)
                .show()
    }
}
