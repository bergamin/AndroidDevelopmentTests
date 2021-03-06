package com.bergamin.finances.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import com.bergamin.finances.R
import com.bergamin.finances.database.DatabaseGenerator
import com.bergamin.finances.database.dao.TransactionDAO
import com.bergamin.finances.model.Transaction
import com.bergamin.finances.model.Type
import com.bergamin.finances.ui.ViewAbstract
import com.bergamin.finances.ui.adapter.TransactionsListAdapter
import com.bergamin.finances.ui.dialog.TransactionDialog
import com.bergamin.finances.ui.dialog.UpdateTransactionDialog
import com.bergamin.finances.util.efEqualsIgnoreScale
import kotlinx.android.synthetic.main.activity_transactions_list.*
import java.math.BigDecimal

class TransactionsListActivity : AppCompatActivity() {

    private lateinit var dao: TransactionDAO
    private lateinit var transactions: List<Transaction>
    // initializes by the time of first usage
    private val activityView by lazy { window.decorView }
    private val activityViewGroup by lazy { activityView as ViewGroup }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transactions_list)

        dao = DatabaseGenerator().generate(this).getTransactionDAO()

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
        transactions = dao.getAll()
        ViewAbstract(this, activityView, transactions).updateTotals()
        with(transactions_listview) {
            adapter = TransactionsListAdapter(transactions, this@TransactionsListActivity)
            setOnItemClickListener { _, _, position, _ ->
                callUpdateDialog(transactions[position])
            }
            setOnCreateContextMenuListener { menu, _, _ ->
                menu.add(Menu.NONE, 1, 1, R.string.remove)
                menu.add(Menu.NONE, 2, 2, R.string.update)
            }
        }
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        val menuID = item?.itemId
        val adapterMenuInfo = item?.menuInfo as AdapterView.AdapterContextMenuInfo
        val position = adapterMenuInfo.position

        when(menuID) {
            1 -> remove(transactions[position])
            2 -> callUpdateDialog(transactions[position])
        }
        return super.onContextItemSelected(item)
    }

    private fun remove(transaction: Transaction) {
        dao.delete(transaction)
        updateTotals()
    }

    private fun callTransactionDialog(type: Type) {
        TransactionDialog(activityViewGroup, this)
                .show(type) {
                    add(it)
                }
    }

    private fun callUpdateDialog(transaction: Transaction) {
        UpdateTransactionDialog(activityViewGroup, this)
                .show(transaction) {
                    it.id = transaction.id
                    update(it)
                }
    }

    private fun add(transaction: Transaction): Boolean {
        if (isValid(transaction, true)) {
            dao.insert(transaction)
            updateTotals()
            transactions_add_menu.close(true)
            return true
        }
        return false
    }

    private fun update(transaction: Transaction): Boolean {
        if (isValid(transaction, true)) {
            dao.update(transaction)
            updateTotals()
            transactions_add_menu.close(true)
            return true
        }
        return false
    }

    private fun isValid(transaction: Transaction, showMessage: Boolean = false): Boolean {
        val errorMessages = mutableListOf<String>()

        if (transaction.value.efEqualsIgnoreScale(BigDecimal.ZERO))
            errorMessages.add(this@TransactionsListActivity.getString(R.string.required_value))
        if (transaction.value < BigDecimal.ZERO)
            errorMessages.add(this@TransactionsListActivity.getString(R.string.negative_value))

        if (errorMessages.size > 0) {
            if (showMessage) {
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
