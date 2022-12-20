package com.twaun95.moviestar.application

import timber.log.Timber

object Logger {

    private const val TAG = "TAEWAUN"

    fun d(message: Any) {
        Timber.tag(TAG).d(message.toString())
    }
}