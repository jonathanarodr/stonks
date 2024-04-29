package br.com.stonks.common.mapper

fun interface Mapper<in I, out O> {

    fun mapper(input: I): O
}
