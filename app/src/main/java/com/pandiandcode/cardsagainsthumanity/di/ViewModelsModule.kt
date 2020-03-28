package com.pandiandcode.cardsagainsthumanity.di

import com.pandiandcode.cardsagainsthumanity.viewModel.BlackCardViewModel
import com.pandiandcode.cardsagainsthumanity.viewModel.MainViewModel
import org.koin.dsl.module

val viewModelsModule = module {
    factory { MainViewModel(get(), get(), get()) }
    factory { BlackCardViewModel(get(), get()) }
}