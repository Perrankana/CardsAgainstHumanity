package com.pandiandcode.helpers

interface Mapper<I, O> {
    fun map(input: I): O
}