package br.com.stonks.common.utils

@Suppress("SwallowedException")
inline fun <reified T : Enum<T>> safeEnumValueOf(name: String): T? {
    return try {
        enumValueOf<T>(name)
    } catch (exception: IllegalArgumentException) {
        null
    } catch (exception: IllegalStateException) {
        null
    }
}
