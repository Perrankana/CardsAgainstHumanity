package com.pandiandcode.cardsagainsthumanity.domain

import com.pandiandcode.cardsagainsthumanity.datasource.PlayingCardsDataSource
import com.pandiandcode.cardsagainsthumanity.datasource.firebasedatasource.Response
import com.pandiandcode.cardsagainsthumanity.domain.model.WhiteCard

class GameManager(private val playingCardsDataSource: PlayingCardsDataSource) {
    suspend fun putPlayingCards(cards: List<WhiteCard>) =
        playingCardsDataSource.putPlayingCards(cards.map { it.description })

    suspend fun getPlayingCards(): Response<List<PlayingCards>> =
        playingCardsDataSource.getPlayingCards().map {
            it.map { cards ->
                PlayingCards(cards.map { description ->
                    WhiteCard(0, description)
                })
            }
        }
}

data class PlayingCards(val cards: List<WhiteCard>)

fun <T, R> Response<T>.map(block: (T) -> R): Response<R> = if (this is Response.Success) {
    Response.Success(block(this.value))
} else {
    Response.Failed()
}