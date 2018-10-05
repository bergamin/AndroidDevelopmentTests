package com.bergamin.finances.util

import com.bergamin.finances.model.Transaction
import com.bergamin.finances.model.Type
import java.math.BigDecimal

fun createExampleData(): MutableList<Transaction>{
    return mutableListOf(Transaction(value = BigDecimal(20.5)
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
