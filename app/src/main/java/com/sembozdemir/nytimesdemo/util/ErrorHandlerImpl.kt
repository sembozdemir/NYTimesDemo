package com.sembozdemir.nytimesdemo.util

import android.content.Context
import com.sembozdemir.nytimesdemo.R
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLPeerUnverifiedException

class ErrorHandlerImpl(private val context: Context) : ErrorHandler {

    override fun getPrettyMessage(e: Exception): String {
        val errorMessageResId = when (e) {
            is ConnectException,
            is SocketException,
            is SocketTimeoutException,
            is UnknownHostException,
            is SSLPeerUnverifiedException -> R.string.connection_error_message
            else -> R.string.general_error_message
        }

        return context.getString(errorMessageResId)
    }

    override fun getEmptyResponseMessage(): String {
        return context.getString(R.string.empty_response_message)
    }
}