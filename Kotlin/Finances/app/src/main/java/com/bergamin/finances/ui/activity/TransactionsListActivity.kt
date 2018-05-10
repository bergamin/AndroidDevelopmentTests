package com.bergamin.finances.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bergamin.finances.R
import com.bergamin.finances.delegate.TransactionDelegate
import com.bergamin.finances.model.Transaction
import com.bergamin.finances.model.Type
import com.bergamin.finances.ui.ViewAbstract
import com.bergamin.finances.ui.adapter.TransactionsListAdapter
import com.bergamin.finances.ui.dialog.TransactionDialog
import com.bergamin.finances.ui.dialog.UpdateTransactionDialog
import com.bergamin.finances.util.efEqualsIgnoreScale
import kotlinx.android.synthetic.main.activity_transactions_list.*
import java.math.BigDecimal

/**
 * Created by Guilherme Taffarel Bergamin on 12/01/2018.
 */
class TransactionsListActivity : AppCompatActivity() {

    private var transactions: MutableList<Transaction> = mutableListOf()
    // initializes by the time of first usage
    private val activityView by lazy { window.decorView }
    private val activityViewGroup by lazy { activityView as ViewGroup }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transactions_list)

        updateTotals()
        configFabAddTransaction()
    }

    private fun configFabAddTransaction() {
        transactions_add_revenue.setOnClickListener {
            callTransactionDialog(Type.REVENUE)
        }
        transactions_add_expense.setOnClickListener {
            callTransactionDialog(Type.EXPENSE)
        }
    }

    private fun updateTotals() {
        ViewAbstract(this, activityView, transactions).updateTotals()
        with(transactions_listview) {
            adapter = TransactionsListAdapter(transactions,this@TransactionsListActivity)
            setOnItemClickListener { _, _, position, _ ->
                val transaction = transactions[position]
                callUpdateDialog(transaction, position)
            }
        }
    }

    private fun callTransactionDialog(type: Type) {
        TransactionDialog(activityViewGroup, this)
                .show(type, object : TransactionDelegate {
                    override fun delegate(transaction: Transaction) {
                        if(add(transaction)) {
                            transactions_add_menu.close(true)
                        }
                    }
                })
    }

    private fun callUpdateDialog(transaction: Transaction, position: Int) {
        UpdateTransactionDialog(activityViewGroup, this)
                .show(transaction, object : TransactionDelegate {
                    override fun delegate(transaction: Transaction) {
                        if (update(position, transaction)) {
                            transactions_add_menu.close(true)
                        }
                    }

                })
    }

    private fun add(transaction: Transaction): Boolean {
        if(isValid(transaction, true)) {
            transactions.add(transaction)
            updateTotals()
            return true
        }
        return false
    }

    private fun update(position: Int, transaction: Transaction): Boolean {
        if(isValid(transaction, true)) {
            transactions[position] = transaction
            updateTotals()
            return true
        }
        return false
    }

    private fun isValid(transaction: Transaction, showMessage: Boolean = false): Boolean{
        val errorMessages = mutableListOf<String>()

        if(transaction.value.efEqualsIgnoreScale(BigDecimal.ZERO))
            errorMessages.add(this@TransactionsListActivity.getString(R.string.required_value))
        if(transaction.value < BigDecimal.ZERO)
            errorMessages.add(this@TransactionsListActivity.getString(R.string.negative_value))

        if(errorMessages.size > 0) {
            if(showMessage) {
                var fullMessage = ""
                if (errorMessages.size == 1) {
                    fullMessage = errorMessages[0]
                } else {
                    for (message in errorMessages) {
                        fullMessage += "- $message\n"
                    }
                }
                Toast.makeText(this@TransactionsListActivity, fullMessage.trim(), Toast.LENGTH_LONG).show()
            }
            return false
        }
        return true
    }
}
