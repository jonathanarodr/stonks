package br.com.stonks.common.android

import android.content.res.AssetManager
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import timber.log.Timber
import java.io.IOException

fun AssetManager.openFile(fileName: String): String? {
    return try {
        open(fileName).use {
            it.bufferedReader().readText()
        }
    } catch (exception: IOException) {
        Timber.e(exception)
        null
    }
}

inline fun <reified T> AssetManager.convertFileTo(fileName: String): T? {
    return try {
        val assetContent = openFile(fileName)
        return Gson().fromJson(assetContent, T::class.java)
    } catch (exception: JsonSyntaxException) {
        Timber.e(exception)
        null
    }
}
