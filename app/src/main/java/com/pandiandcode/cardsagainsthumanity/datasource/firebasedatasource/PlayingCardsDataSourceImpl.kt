package com.pandiandcode.cardsagainsthumanity.datasource.firebasedatasource

import com.pandiandcode.cardsagainsthumanity.datasource.PlayingCardsDataSource

class PlayingCardsDataSourceImpl() :
    PlayingCardsDataSource {

    override suspend fun getPlayingCards(): Response<List<List<String>>> {
        return Response.Failed()
    }

    override suspend fun putPlayingCards(cards: List<String>): Response<Unit> {
        return Response.Failed()
    }
}

sealed class Response<T> {
    class Success<T>(val value: T) : Response<T>()
    class Failed<T> : Response<T>()
}