package com.pandiandcode.cardsagainsthumanity.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pandiandcode.cardsagainsthumanity.datasource.firebasedatasource.Result
import com.pandiandcode.cardsagainsthumanity.domain.GameManager
import com.pandiandcode.cardsagainsthumanity.domain.WhiteDeckRepository
import com.pandiandcode.cardsagainsthumanity.domain.model.WhiteCard
import com.pandiandcode.cardsagainsthumanity.domain.usecases.GetBlackCard
import kotlinx.coroutines.*

class MainViewModel(
    private val getBlackCard: GetBlackCard,
    private val whiteDeckRepository: WhiteDeckRepository,
    private val gameManager: GameManager
) : ViewModel(), CoroutineScope by MainScope() {

    private val _whiteCards: MutableLiveData<List<WhiteCard>> = MutableLiveData(emptyList())
    private val _playingWhiteCards: MutableLiveData<PlayingWhiteCards> = MutableLiveData(
        PlayingWhiteCards(
            mutableListOf(),
            { onSubmitCards() },
            { onRemoveCardFromGame(it) })
    )
    private val _navigateToBlackCard: MutableLiveData<Boolean> = MutableLiveData()

    private val _error: MutableLiveData<ErrorViewModel> =
        MutableLiveData(ErrorViewModel(show = false, close = {
            closeError()
        }))

    val whiteCards: LiveData<List<WhiteCard>> = _whiteCards
    val playingWhiteCards: LiveData<PlayingWhiteCards> = _playingWhiteCards
    val navigateToBlackCard: LiveData<Boolean> = _navigateToBlackCard
    val error: LiveData<ErrorViewModel> = _error

    val onBlackDeckClick: () -> Unit = {
        _navigateToBlackCard.value = true
    }

    val onWhiteDeckClick: () -> Unit = {
        onWhiteDeck()
    }

    val onWhiteCardClick: (WhiteCard) -> Unit = {
        onWhiteCard(it)
    }

    val closeError: () -> Unit = {
        _error.value = _error.value?.copy(show = false, message = "")
    }

    fun resetNavigation(){
        _navigateToBlackCard.value = false
    }
    private fun onWhiteCard(card: WhiteCard) {
        _whiteCards.value = _whiteCards.value?.filterNot {
            it == card
        }

        _playingWhiteCards.value?.let { playingCards ->
            _playingWhiteCards.value = playingCards.copy(cards = playingCards.cards.also {
                it.add(card)
            })
        }
    }

    private fun onWhiteDeck() {
        launch {
            val whiteCardsList = withContext(Dispatchers.IO) {
                whiteDeckRepository.getDeck()
            }.cards.shuffled()
            val cardsToPick = 10 - (_whiteCards.value?.size ?: 0)
            _whiteCards.value =
                whiteCardsList.subList(0, cardsToPick) + (_whiteCards.value ?: emptyList())
        }
    }

    private fun onSubmitCards() {
        _playingWhiteCards.value?.let { playingCards ->
            launch {
                val response: Result<Unit> = withContext(Dispatchers.IO) {
                    gameManager.putPlayingCards(playingCards.cards.toList())
                }
                when (response) {
                    is Result.Success -> _playingWhiteCards.value =
                        playingCards.copy(cards = mutableListOf())
                    is Result.Failed -> _error.value = _error.value?.copy(
                        show = true,
                        message = "Dile a Rocío que esto ha petao por: ${response.exception.localizedMessage}"
                    )
                }
            }
        }
    }

    private fun onRemoveCardFromGame(card: WhiteCard) {
        _playingWhiteCards.value?.let { playingCards ->
            _playingWhiteCards.value = playingCards.copy(cards = playingCards.cards.also {
                it.remove(card)
            })
        }
        _whiteCards.value = _whiteCards.value?.toMutableList()?.also {
            it.add(card)
        }?.toList()
    }
}

data class PlayingWhiteCards(
    val cards: MutableList<WhiteCard>,
    val submit: () -> Unit,
    val removeCard: (WhiteCard) -> Unit
) {
    val visible = cards.isEmpty()
}

data class ErrorViewModel(
    val show: Boolean,
    val close: () -> Unit,
    val message: String = ""
)