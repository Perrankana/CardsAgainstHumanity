package com.pandiandcode.cardsagainsthumanity.datasource

import com.pandiandcode.cardsagainsthumanity.datasource.firebasedatasource.Response

interface PlayingCardsDataSource {
    suspend fun getPlayingCards(): Response<List<List<String>>>
    suspend fun putPlayingCards(cards: List<String>): Response<Unit>
}