package com.pandiandcode.cardsagainsthumanity.datasource

import com.pandiandcode.cardsagainsthumanity.datasource.firebasedatasource.NewPlayingCardsApiData
import com.pandiandcode.cardsagainsthumanity.datasource.firebasedatasource.PlayingCardsApiData
import com.pandiandcode.cardsagainsthumanity.datasource.firebasedatasource.Result


interface PlayingCardsDataSource {
    suspend fun getPlayingCards(): Result<List<PlayingCardsApiData>>
    suspend fun putPlayingCards(cards: NewPlayingCardsApiData): Result<Unit>
    suspend fun clearCards(): Result<Unit>
}