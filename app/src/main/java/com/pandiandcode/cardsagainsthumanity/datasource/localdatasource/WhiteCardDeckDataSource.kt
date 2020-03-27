package com.pandiandcode.cardsagainsthumanity.datasource.localdatasource

import com.google.gson.Gson
import com.pandiandcode.helpers.FileJSONReader

class WhiteCardDeckDataSource(val reader: FileJSONReader) {

    fun getWhiteCardDTOs() = Gson().fromJson(
        reader.read("whiteDeck.json")
        , Array<WhiteCardDTO>::class.java
    ).toList()

}