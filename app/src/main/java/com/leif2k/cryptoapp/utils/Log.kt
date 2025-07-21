package com.leif2k.cryptoapp.utils

import android.util.Log

const val LOG_TAG = "log_tag"

fun log(className: String, message: String) {
    Log.d(LOG_TAG, "$className: $message")
}