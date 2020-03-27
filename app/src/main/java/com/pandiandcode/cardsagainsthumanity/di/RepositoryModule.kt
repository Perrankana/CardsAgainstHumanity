package com.pandiandcode.cardsagainsthumanity.di

import com.pandiandcode.cardsagainsthumanity.domain.*
import org.koin.dsl.module

val repositoryModule = module {
    single<BlackDeckRepository> { BlackDeckRepositoryImp(get()) }
    single<WhiteDeckRepository> { WhiteDeckRepositoryImp(get()) }
    single{ GameManager(get())}
}