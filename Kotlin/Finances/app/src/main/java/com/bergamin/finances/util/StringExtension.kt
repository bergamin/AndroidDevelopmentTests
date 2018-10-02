package com.bergamin.finances.util

fun String.efLimitsIn(length: Int): String {
    if(this.length > length){
        return "${this.substring(0,length)}..." // TODO: make this variable and work well for different screen sizes
    }
    return this
}
