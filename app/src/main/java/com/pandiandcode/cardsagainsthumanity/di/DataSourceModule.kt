package com.pandiandcode.cardsagainsthumanity.di

import com.pandiandcode.cardsagainsthumanity.datasource.PlayingCardsDataSource
import com.pandiandcode.cardsagainsthumanity.datasource.firebasedatasource.PlayingCardsDataSourceImpl
import com.pandiandcode.cardsagainsthumanity.datasource.localdatasource.BlackCardDeckDataSource
import com.pandiandcode.cardsagainsthumanity.datasource.localdatasource.WhiteCardDeckDataSource
import com.pandiandcode.helpers.FileJSONReader
import org.koin.dsl.module

val databaseModule = module {
    single { BlackCardDeckDataSource(get()) }
    single { WhiteCardDeckDataSource(get()) }
    single { FileJSONReader(get()) }
    single<PlayingCardsDataSource> { PlayingCardsDataSourceImpl() }
}

