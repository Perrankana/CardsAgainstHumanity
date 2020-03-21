package com.pandiandcode.cardsagainsthumanity.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pandiandcode.cardsagainsthumanity.localdatasource.BlackCardDeckDataSource
import com.pandiandcode.cardsagainsthumanity.R
import com.pandiandcode.cardsagainsthumanity.domain.*
import com.pandiandcode.cardsagainsthumanity.localdatasource.BlackCardDTO
import com.pandiandcode.cardsagainsthumanity.localdatasource.WhiteCardDeckDataSource
import com.pandiandcode.helpers.FileJSONReader
import com.pandiandcode.helpers.ListMapperImpl
import com.pandiandcode.helpers.Mapper

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val jsonReader = FileJSONReader(applicationContext)

        val blackCardDeckDataSource = BlackCardDeckDataSource(jsonReader)
        val whiteCardDeckDataSource = WhiteCardDeckDataSource(jsonReader)

        val blackDeckRepository = BlackDeckRepositoryImp(blackCardDeckDataSource)
        val whiteDeckRepository = WhiteDeckRepositoryImp(whiteCardDeckDataSource)

        val whiteDeck = whiteDeckRepository.getDeck()
        val blackDeck = blackDeckRepository.getDeck()



    }
}
