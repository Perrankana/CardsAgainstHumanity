package com.pandiandcode.cardsagainsthumanity.di

import com.pandiandcode.cardsagainsthumanity.viewModel.MainViewModel
import org.koin.dsl.module

val viewModelsModule = module {
    factory { MainViewModel(get(), get(), get()) }
}