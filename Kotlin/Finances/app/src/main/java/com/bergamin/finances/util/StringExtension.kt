package com.bergamin.finances.util

/**
 * Created by Guilherme Taffarel Bergamin on 17/02/2018.
 */

fun String.efLimitsIn(length: Int): String{
    if(this.length > length){
        return "${this.substring(0,length)}..." // TODO: make this variable and work well for different screen sizes
    }
    return this
}
