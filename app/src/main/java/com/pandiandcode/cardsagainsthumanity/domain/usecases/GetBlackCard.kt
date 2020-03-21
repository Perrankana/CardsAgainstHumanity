package com.pandiandcode.cardsagainsthumanity.domain.usecases

import com.pandiandcode.cardsagainsthumanity.domain.BlackCard
import com.pandiandcode.cardsagainsthumanity.domain.BlackDeckRepository
import java.util.*

class GetBlackCard(private val blackDeckRepository: BlackDeckRepository) {
    operator fun invoke() : BlackCard {
        val blackDeck = blackDeckRepository.getDeck().cards
        val random = Random().nextInt(blackDeck.size - 0)
        return blackDeck[random]
    }
}