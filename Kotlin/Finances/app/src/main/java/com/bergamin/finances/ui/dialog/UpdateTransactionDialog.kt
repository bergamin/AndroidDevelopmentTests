package com.bergamin.finances.ui.dialog

import android.content.Context
import android.view.ViewGroup
import com.bergamin.finances.R
import com.bergamin.finances.model.Transaction
import com.bergamin.finances.model.Type
import com.bergamin.finances.util.efFormat

/**
 * Created by Guilherme Taffarel Bergamin on 26/03/2018.
 */
class UpdateTransactionDialog(viewGroup: ViewGroup,
                              private val context: Context) : FormTransactionDialog(viewGroup, context) {

    override val positiveButtonTitle = R.string.update

    fun show(transaction: Transaction, delegate: (transaction: Transaction) -> Unit) {
        val type = transaction.type
        val categories = context.resources.getStringArray(categoriesByType(type))
        val position = categories.indexOf(transaction.category)

        super.show(type, delegate)

        value.setText(transaction.value.toString())
        date.setText(transaction.date.efFormat(context))
        category.setSelection(position, true)
    }

    override fun titleByType(type: Type): Int {
        if (type == Type.REVENUE) {
            return R.string.update_revenue
        }
        return R.string.update_expense
    }
}
