package com.leif2k.cryptoapp.common

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone


fun convertTimestampToTime(timestamp: Long?): String {
    if (timestamp == null) return ""
    val stamp = Timestamp(timestamp * 1000)
    val date = Date(stamp.time)
    val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    sdf.timeZone = TimeZone.getDefault()
    return sdf.format(date)
}