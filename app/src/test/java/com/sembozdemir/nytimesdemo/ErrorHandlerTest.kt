package com.sembozdemir.nytimesdemo

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.sembozdemir.nytimesdemo.util.ErrorHandler
import com.sembozdemir.nytimesdemo.util.ErrorHandlerImpl
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLPeerUnverifiedException

@RunWith(RobolectricTestRunner::class)
class ErrorHandlerTest {

    private lateinit var context: Context

    private val connectionErrorMessage: String
        get() = context.getString(R.string.connection_error_message)

    private val generalErrorMessage: String
        get() = context.getString(R.string.general_error_message)

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext<Context>()
    }

    @Test
    fun `getPrettyMessage should return proper error message in case of ConnectException`() {

        val errorHandler: ErrorHandler = ErrorHandlerImpl(context)

        val result = errorHandler.getPrettyMessage(ConnectException())

        assertEquals(connectionErrorMessage, result)
    }

    @Test
    fun `getPrettyMessage should return proper error message in case of SocketException`() {

        val errorHandler: ErrorHandler = ErrorHandlerImpl(context)

        val result = errorHandler.getPrettyMessage(SocketException())

        assertEquals(connectionErrorMessage, result)
    }

    @Test
    fun `getPrettyMessage should return proper error message in case of SocketTimeoutException`() {

        val errorHandler: ErrorHandler = ErrorHandlerImpl(context)

        val result = errorHandler.getPrettyMessage(SocketTimeoutException())

        assertEquals(connectionErrorMessage, result)
    }

    @Test
    fun `getPrettyMessage should return proper error message in case of UnknownHostException`() {

        val errorHandler: ErrorHandler = ErrorHandlerImpl(context)

        val result = errorHandler.getPrettyMessage(UnknownHostException())

        assertEquals(connectionErrorMessage, result)
    }

    @Test
    fun `getPrettyMessage should return proper error message in case of SSLPeerUnverifiedException`() {

        val errorHandler: ErrorHandler = ErrorHandlerImpl(context)

        val result = errorHandler.getPrettyMessage(SSLPeerUnverifiedException("reason"))

        assertEquals(connectionErrorMessage, result)
    }

    @Test
    fun `getPrettyMessage should return proper error message in case of any Exception`() {

        val errorHandler: ErrorHandler = ErrorHandlerImpl(context)

        val result = errorHandler.getPrettyMessage(Exception())

        assertEquals(generalErrorMessage, result)
    }
}