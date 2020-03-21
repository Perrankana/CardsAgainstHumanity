package com.pandiandcode.cardsagainsthumanity.viewModel

import com.pandiandcode.cardsagainsthumanity.domain.BlackCard

data class BlackCardViewModel(
    val card: BlackCard? = null,
    val visible: Boolean,
    val onBlackDeckClose: () -> Unit
){
    fun showDraw() = card?.draw?.let { it > 1 } ?: false
    fun showPick() = card?.pick?.let { it > 1 } ?: false
}