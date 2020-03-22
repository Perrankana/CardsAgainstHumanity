package com.pandiandcode.cardsagainsthumanity.di

import com.pandiandcode.cardsagainsthumanity.domain.usecases.GetBlackCard
import org.koin.dsl.module

val useCasesModule = module {
    factory { GetBlackCard(get()) }
}