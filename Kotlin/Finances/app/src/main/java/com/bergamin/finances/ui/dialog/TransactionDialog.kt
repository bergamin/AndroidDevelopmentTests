package com.bergamin.finances.ui.dialog

import android.content.Context
import android.view.ViewGroup
import com.bergamin.finances.R
import com.bergamin.finances.model.Type

/**
 * Created by Guilherme Taffarel Bergamin on 01/03/2018.
 */
class TransactionDialog(viewGroup: ViewGroup,
                        context: Context) : FormTransactionDialog(viewGroup, context) {

    override val positiveButtonTitle = R.string.add

    override fun titleByType(type: Type): Int {
        if (type == Type.REVENUE) {
            return R.string.add_revenue
        }
        return R.string.add_expense
    }
}
