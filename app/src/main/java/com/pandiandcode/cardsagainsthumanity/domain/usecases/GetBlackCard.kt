package com.pandiandcode.cardsagainsthumanity.domain.usecases

import com.pandiandcode.cardsagainsthumanity.domain.BlackDeckRepository
import com.pandiandcode.cardsagainsthumanity.domain.Card
import java.util.*

class GetBlackCard(private val blackDeckRepository: BlackDeckRepository) {
    operator fun invoke() : Card {
        val blackDeck = blackDeckRepository.load()
        val random = Random().nextInt(blackDeck.size - 0)
        return blackDeck[random]
    }
}