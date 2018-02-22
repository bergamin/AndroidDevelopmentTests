package com.bergamin.finances.util

import android.content.Context
import com.bergamin.finances.R
import com.bergamin.finances.model.Transaction
import com.bergamin.finances.model.Type
import java.math.BigDecimal
import java.text.SimpleDateFormat

/**
 * Created by Guilherme Taffarel Bergamin on 19/02/2018.
 */

fun createExampleData(): List<Transaction>{
    return listOf(Transaction(value = BigDecimal(20.5)
                             ,category = "Food"
                             ,type = Type.EXPENSE)
                 ,Transaction(value = BigDecimal(100)
                             ,category = "Savings"
                             ,type = Type.REVENUE)
                 ,Transaction(value = BigDecimal(50)
                             ,category = "Clothing"
                             ,type = Type.EXPENSE)
                 ,Transaction(value = BigDecimal(2000)
                             ,category = "Loan"
                             ,type = Type.EXPENSE)
                 ,Transaction(value = BigDecimal(1000)
                             ,category = "Salary"
                             ,type = Type.REVENUE)
    )
}

fun localeDateFormat(context: Context): SimpleDateFormat {
    // TODO: change this automatically based on location
    return SimpleDateFormat(context.getString(R.string.date_format))
}
