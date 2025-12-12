package com.apparel.offprice.common.utils

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.SelectableDates
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@RequiresApi(Build.VERSION_CODES.O)
object PastOrPresentSelectableDates : SelectableDates {

    override fun isSelectableDate(utcTimeMillis: Long): Boolean {
        val selectedDate = Instant.ofEpochMilli(utcTimeMillis).atZone(ZoneId.systemDefault()).toLocalDate()
        val currentDate = LocalDate.now()
        return !selectedDate.isAfter(currentDate)
    }


    override fun isSelectableYear(year: Int): Boolean {
        return year <= LocalDate.now().year
    }
}