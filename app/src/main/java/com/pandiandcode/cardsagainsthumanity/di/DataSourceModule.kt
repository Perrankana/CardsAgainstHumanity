package com.pandiandcode.cardsagainsthumanity.di

import com.pandiandcode.cardsagainsthumanity.localdatasource.BlackCardDeckDataSource
import com.pandiandcode.cardsagainsthumanity.localdatasource.WhiteCardDeckDataSource
import com.pandiandcode.helpers.FileJSONReader
import org.koin.dsl.module

val databaseModule = module {
    single { BlackCardDeckDataSource(get()) }
    single { WhiteCardDeckDataSource(get()) }
    single { FileJSONReader(get()) }
}

