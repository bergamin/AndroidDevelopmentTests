package com.bergamin.finances.ui.dialog

import android.app.DatePickerDialog
import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bergamin.finances.R
import com.bergamin.finances.delegate.TransactionDelegate
import com.bergamin.finances.model.Transaction
import com.bergamin.finances.model.Type
import com.bergamin.finances.util.*
import kotlinx.android.synthetic.main.form_transaction.view.*
import java.util.*

/**
 * Created by Guilherme Taffarel Bergamin on 26/03/2018.
 */
class UpdateTransactionDialog(private val viewGroup: ViewGroup,
                              private val context: Context) {

    private val dialog = createLayout()
    private val value = dialog.form_transaction_value
    private val date = dialog.form_transaction_date
    private val category = dialog.form_transaction_category

    fun show(transaction: Transaction, delegate: TransactionDelegate) {
        val type = transaction.type
        val categories = context.resources.getStringArray(categoriesByType(type))
        val position = categories.indexOf(transaction.category)

        configDateField()
        configCategoryField(type)
        configForm(type, delegate)

        value.setText(transaction.value.toString())
        date.setText(transaction.date.efFormat(context))
        category.setSelection(position, true)
    }

    private fun createLayout(): View {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.form_transaction, viewGroup, false)
    }

    private fun configDateField() {
        val today = Calendar.getInstance()

        with(date) {
            setText(today.efFormat(context))
            setOnClickListener {
                DatePickerDialog(context, { _, year, month, day ->
                    val selectedDate = Calendar.getInstance()
                    selectedDate.set(year, month, day)
                    this.setText(selectedDate.efFormat(context))
                }, today.efYear(), today.efMonth(), today.efDay())
                        .show()
            }
        }
    }

    private fun configCategoryField(type: Type) {
        val categories = categoriesByType(type)
        val adapter = ArrayAdapter.createFromResource(context, categories, android.R.layout.simple_spinner_dropdown_item)
        category.adapter = adapter
    }

    private fun configForm(type: Type, delegate: TransactionDelegate) {
        val title = titleByType(type)
        val alertDialog = AlertDialog.Builder(context)
                .setTitle(title)
                .setView(dialog)
                .setPositiveButton(R.string.update, null)
                .setNegativeButton(R.string.cancel, null)
                .create()

        alertDialog.setOnShowListener {
            val button = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
            button.setOnClickListener {
                val value = value.text.toString().efParseBigDecimal()
                val date = date.text.toString().efParseCalendar(context)
                val category = category.selectedItem.toString()
                val transaction = Transaction(
                        type = type,
                        value = value,
                        date = date,
                        category = category)

                if(delegate.delegate(transaction)){
                    alertDialog.dismiss()
                }
            }
        }
        alertDialog.show()
    }

    private fun titleByType(type: Type): Int {
        if (type == Type.REVENUE) {
            return R.string.update_revenue
        }
        return R.string.update_expense
    }

    private fun categoriesByType(type: Type): Int {
        if (type == Type.REVENUE) {
            return R.array.revenue_categories
        }
        return R.array.expense_categories
    }
}
