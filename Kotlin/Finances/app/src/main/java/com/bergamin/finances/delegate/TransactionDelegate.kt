package com.bergamin.finances.delegate

import com.bergamin.finances.model.Transaction

/**
 * Created by Guilherme Taffarel Bergamin on 02/03/2018.
 */
interface TransactionDelegate {

    fun delegate(transaction: Transaction)

}