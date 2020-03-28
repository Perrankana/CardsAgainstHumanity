package com.pandiandcode.cardsagainsthumanity.datasource.firebasedatasource

data class PlayingCardsApiData(val _id: String, val cards: List<CardApiData>)

data class NewPlayingCardsApiData(val cards: List<CardApiData>)

data class CardApiData(val description: String)