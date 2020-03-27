package com.pandiandcode.cardsagainsthumanity.domain

import com.pandiandcode.cardsagainsthumanity.domain.model.BlackCard
import com.pandiandcode.cardsagainsthumanity.domain.model.BlackCardDeck
import com.pandiandcode.cardsagainsthumanity.datasource.localdatasource.BlackCardDTO
import com.pandiandcode.cardsagainsthumanity.datasource.localdatasource.BlackCardDeckDataSource

interface BlackDeckRepository {
    fun getDeck(): BlackCardDeck
}

class BlackDeckRepositoryImp(
    private val blackCardDeckDataSource: BlackCardDeckDataSource
) : BlackDeckRepository {
    override fun getDeck() =
        BlackCardDeck(
            blackCardDeckDataSource.getBlackCardDTOs().toDomain
        )
}

val List<BlackCardDTO>.toDomain: List<BlackCard>
    get() = map { it.toDomain }

val BlackCardDTO.toDomain: BlackCard
    get() = BlackCard(
        id,
        description,
        pick,
        draw
    )