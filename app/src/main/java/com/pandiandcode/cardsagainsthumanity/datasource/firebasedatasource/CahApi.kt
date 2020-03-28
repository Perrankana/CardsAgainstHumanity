package com.pandiandcode.cardsagainsthumanity.datasource.firebasedatasource

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*


interface CahApi {
    @GET("playingcards")
    fun getPlayingCards(): Deferred<Response<List<PlayingCardsApiData>>>

    @POST("playingcards")
    fun putPlayingCards(@Body playingCards: NewPlayingCardsApiData): Deferred<Response<Unit>>

    @DELETE("playingcards/{id}")
    fun deleteCard(@Path("id") id: String): Deferred<Response<Unit>>
}