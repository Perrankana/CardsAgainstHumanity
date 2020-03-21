package com.pandiandcode.helpers

import android.app.Application
import android.content.Context

class FileJSONReader(private val applicationContext: Context) {
    fun read(fileName: String) = applicationContext.assets.open(fileName).bufferedReader().use { it.readText() }
}