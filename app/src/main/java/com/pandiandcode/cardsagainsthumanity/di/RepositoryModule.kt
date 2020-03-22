package com.pandiandcode.cardsagainsthumanity.di

import com.pandiandcode.cardsagainsthumanity.domain.BlackDeckRepository
import com.pandiandcode.cardsagainsthumanity.domain.BlackDeckRepositoryImp
import com.pandiandcode.cardsagainsthumanity.domain.WhiteDeckRepository
import com.pandiandcode.cardsagainsthumanity.domain.WhiteDeckRepositoryImp
import org.koin.dsl.module

val repositoryModule = module {
    single<BlackDeckRepository> { BlackDeckRepositoryImp(get()) }
    single<WhiteDeckRepository> { WhiteDeckRepositoryImp(get()) }
}