package com.pandiandcode.cardsagainsthumanity.domain

import com.pandiandcode.cardsagainsthumanity.domain.model.WhiteCard
import com.pandiandcode.cardsagainsthumanity.domain.model.WhiteCardDeck
import com.pandiandcode.cardsagainsthumanity.localdatasource.WhiteCardDTO
import com.pandiandcode.cardsagainsthumanity.localdatasource.WhiteCardDeckDataSource

interface WhiteDeckRepository {
    fun getDeck(): WhiteCardDeck
}

class WhiteDeckRepositoryImp(val whiteCardDeckDataSource: WhiteCardDeckDataSource): WhiteDeckRepository {
    override fun getDeck() =
        WhiteCardDeck(
            whiteCardDeckDataSource.getWhiteCardDTOs().toDomain
        )
}

val List<WhiteCardDTO>.toDomain: List<WhiteCard>
    get() = map { it.toDomain }

val WhiteCardDTO.toDomain: WhiteCard
    get() = WhiteCard(
        id,
        description
    )