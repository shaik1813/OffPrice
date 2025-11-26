package com.apparel.offprice.common.utils



fun String.takeInitials(): String{
    return this.trim()
        .splitToSequence(" ").take(2).map{it.first()}
        .joinToString("").uppercase()
}