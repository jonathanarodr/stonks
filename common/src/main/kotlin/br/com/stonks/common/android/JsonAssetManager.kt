package br.com.stonks.common.android

import android.content.res.AssetManager
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import timber.log.Timber
import java.io.IOException

class JsonAssetManager(
    val assets: AssetManager,
) {

    inline fun <reified T> openFile(fileName: String): T? {
        return assets.openFile(fileName)
    }
}

inline fun <reified T> AssetManager.openFile(fileName: String): T? {
    return try {
        val assetContent: String = open(fileName).use {
            it.bufferedReader().readText()
        }.also {
            Timber.e("jon::$it") // fixme remove after test
        }

        return Gson().fromJson(assetContent, T::class.java)
    } catch (exception: IOException) {
        Timber.e(exception)
        null
    } catch (exception: JsonSyntaxException) {
        Timber.e(exception)
        null
    }
}
