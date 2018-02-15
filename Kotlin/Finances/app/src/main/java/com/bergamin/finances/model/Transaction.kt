package com.bergamin.finances.model

import java.math.BigDecimal
import java.util.Calendar

/**
 * Created by taffa on 12/02/2018.
 */
class Transaction(val value: BigDecimal,
                  val category: String,
                  val date: Calendar) {
}