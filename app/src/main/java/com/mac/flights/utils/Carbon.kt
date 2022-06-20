package com.mac.flights.utils

import java.text.SimpleDateFormat
import java.util.*

class Carbon {
    companion object{

        fun parseDate(time: Long): String{
            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val calendar = Calendar.getInstance()
            try {
                calendar.timeInMillis = time * 1000L
            }catch (e: Exception){
                e.printStackTrace()
            }
            return formatter.format(calendar.time)
        }

        fun getCurrentDate(): String {
            val c = Calendar.getInstance().time
            val df = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            return df.format(c)
        }
    }


}