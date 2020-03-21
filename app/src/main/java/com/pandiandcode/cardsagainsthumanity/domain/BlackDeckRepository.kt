package com.pandiandcode.cardsagainsthumanity.domain

import com.pandiandcode.cardsagainsthumanity.localdatasource.BlackCardDTO
import com.pandiandcode.cardsagainsthumanity.localdatasource.BlackCardDeckDataSource
import com.pandiandcode.helpers.ListMapper
import com.pandiandcode.helpers.Mapper

interface BlackDeckRepository {
    fun getDeck(): BlackCardDeck
}

class BlackDeckRepositoryImp(private val blackCardDeckDataSource: BlackCardDeckDataSource
) {
    fun getDeck() = BlackCardDeck(blackCardDeckDataSource.getBlackCardDTOs().toDomain)
}

val List<BlackCardDTO>.toDomain: List<BlackCard>
    get() = map { it.toDomain }

val BlackCardDTO.toDomain: BlackCard
    get() = BlackCard(id, description, pick, draw)