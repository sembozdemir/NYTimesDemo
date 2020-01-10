package com.sembozdemir.nytimesdemo.core.extensions

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.sembozdemir.nytimesdemo.R

fun Context.customTab(url: String, init: (CustomTabsIntent.Builder.() -> Unit)? = null) {

    val customTabBuilder = CustomTabsIntent.Builder()
        .setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary))
        .setShowTitle(true)

    init?.let { customTabBuilder.init() }

    customTabBuilder.build().launchUrl(this, Uri.parse(url))
}

