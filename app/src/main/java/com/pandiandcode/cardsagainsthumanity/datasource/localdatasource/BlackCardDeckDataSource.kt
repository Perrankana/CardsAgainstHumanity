package com.pandiandcode.cardsagainsthumanity.datasource.localdatasource

import com.google.gson.Gson
import com.pandiandcode.helpers.FileJSONReader

class BlackCardDeckDataSource(private val reader: FileJSONReader) {
    private val data: MutableList<BlackCardDTO> = mutableListOf()

    fun getBlackCardDTOs(): List<BlackCardDTO> {
        if (data.isEmpty()) {
            data.addAll(loadFromReader())
        }
        return data
    }

    private fun loadFromReader(): List<BlackCardDTO> {
        return Gson().fromJson(
            reader.read("blackDeck.json")
            , Array<BlackCardDTO>::class.java
        ).toList()
    }

}