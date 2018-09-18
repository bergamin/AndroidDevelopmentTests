package com.bergamin.finances.model

import java.math.BigDecimal
import java.util.Calendar

/**
 * Created by Guilherme Taffarel Bergamin on 12/02/2018.
 */
class Transaction(val value: BigDecimal
                 ,val category: String = "Undefined"
                 ,val date: Calendar = Calendar.getInstance()
                 ,val type: Type)
