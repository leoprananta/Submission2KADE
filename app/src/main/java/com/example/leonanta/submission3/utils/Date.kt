package com.example.leonanta.submission3.utils

import java.text.ParseException
import java.text.SimpleDateFormat

object Date {

    private fun formatDate(date: String, format: String): String {
        var finalresult = ""
        val old = SimpleDateFormat("yyyy-MM-dd")
        try {
            val oldDate = old.parse(date)
            val newDate = SimpleDateFormat(format)
            finalresult = newDate.format(oldDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return finalresult
    }

    fun getDate(date: String): String {
        return formatDate(date, "EEE, dd MMM yyyy")
    }
}
