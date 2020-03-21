package com.pandiandcode.cardsagainsthumanity.viewModel

import androidx.lifecycle.ViewModel
import com.pandiandcode.cardsagainsthumanity.domain.BlackDeckRepository
import com.pandiandcode.cardsagainsthumanity.domain.WhiteDeckRepository

class MainViewModel(
    private val blackDeckRepository: BlackDeckRepository,
    private val whiteDeckRepository: WhiteDeckRepository
) : ViewModel() {
    
}