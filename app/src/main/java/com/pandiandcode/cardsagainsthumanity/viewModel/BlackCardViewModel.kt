package com.pandiandcode.cardsagainsthumanity.viewModel

import com.pandiandcode.cardsagainsthumanity.domain.Card

data class BlackCardViewModel(
    val card: Card? = null,
    val visible: Boolean,
    val onBlackDeckClose: () -> Unit
){
    fun showDraw() = card?.draw?.let { it > 0 } ?: false
    fun showPick() = card?.pick?.let { it > 1 } ?: false
}