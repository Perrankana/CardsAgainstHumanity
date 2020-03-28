package com.pandiandcode.cardsagainsthumanity.domain

import com.pandiandcode.cardsagainsthumanity.datasource.PlayingCardsDataSource
import com.pandiandcode.cardsagainsthumanity.datasource.firebasedatasource.CardApiData
import com.pandiandcode.cardsagainsthumanity.datasource.firebasedatasource.NewPlayingCardsApiData
import com.pandiandcode.cardsagainsthumanity.datasource.firebasedatasource.Result
import com.pandiandcode.cardsagainsthumanity.domain.model.WhiteCard

class GameManager(private val playingCardsDataSource: PlayingCardsDataSource) {
    suspend fun putPlayingCards(cards: List<WhiteCard>) =
        playingCardsDataSource.putPlayingCards(NewPlayingCardsApiData(cards = cards.map { CardApiData(it.description) }) )

    suspend fun getPlayingCards(): Result<List<PlayingCards>> =
        playingCardsDataSource.getPlayingCards().map {
            it.map { cards ->
                PlayingCards(cards.cards.map { description ->
                    WhiteCard(0, description.description)
                })
            }
        }

    suspend fun clearCards(): Result<Unit> = playingCardsDataSource.clearCards()
}

data class PlayingCards(val cards: List<WhiteCard>)

fun <T, R>  Result<T>.map(block: (T) -> R):  Result<R> = if (this is  Result.Success) {
    Result.Success(block(this.value))
} else {
    Result.Failed((this as Result.Failed).exception)
}