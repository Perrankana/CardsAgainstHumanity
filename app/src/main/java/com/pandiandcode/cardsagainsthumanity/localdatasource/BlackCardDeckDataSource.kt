package com.pandiandcode.cardsagainsthumanity.localdatasource

import com.google.gson.Gson
import com.pandiandcode.helpers.FileJSONReader

class BlackCardDeckDataSource(val reader: FileJSONReader) {

    fun getBlackCardDTOs(): List<BlackCardDTO> = Gson().fromJson(
            reader.read("blackDeck.json")
            , Array<BlackCardDTO>::class.java
        ).toList()

}