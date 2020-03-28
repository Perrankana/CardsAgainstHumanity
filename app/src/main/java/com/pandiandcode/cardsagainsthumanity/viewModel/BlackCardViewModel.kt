package com.pandiandcode.cardsagainsthumanity.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pandiandcode.cardsagainsthumanity.datasource.firebasedatasource.Result
import com.pandiandcode.cardsagainsthumanity.domain.GameManager
import com.pandiandcode.cardsagainsthumanity.domain.model.BlackCard
import com.pandiandcode.cardsagainsthumanity.domain.model.WhiteCard
import com.pandiandcode.cardsagainsthumanity.domain.usecases.GetBlackCard
import kotlinx.coroutines.*

class BlackCardViewModel(
    private val getBlackCard: GetBlackCard,
    private val gameManager: GameManager
) : ViewModel(), CoroutineScope by MainScope() {

    private val _card: MutableLiveData<BlackCard> = MutableLiveData()
    private val _visible: MutableLiveData<Boolean> = MutableLiveData(true)
    private val _responses: MutableLiveData<List<WhiteCard>> = MutableLiveData(emptyList())

    private val _error: MutableLiveData<ErrorViewModel> =
        MutableLiveData(ErrorViewModel(show = false, close = {
            closeError()
        }))

    val card: LiveData<BlackCard> = _card
    val visible: LiveData<Boolean> = _visible
    val responses: LiveData<List<WhiteCard>> = _responses

    val onBlackDeckClose: () -> Unit = { onBlackCardClose() }

    val onResponses: () -> Unit = { getResponses() }

    val error: LiveData<ErrorViewModel> = _error

    val closeError: () -> Unit = {
        _error.value = _error.value?.copy(show = false, message = "")
    }

    fun load() {
        onBlackDeck()
    }

    fun showDraw() = _card.value?.draw?.let { it > 1 } ?: false
    fun showPick() = _card.value?.pick?.let { it > 1 } ?: false

    private fun onBlackCardClose() {
        _card.value = null
        _visible.value = false
    }

    private fun onBlackDeck() {
        launch {
            val card = withContext(Dispatchers.IO) {
                getBlackCard()
            }
            _card.value = card
            _visible.value = true

            val result = withContext(Dispatchers.IO) {
                gameManager.clearCards()
            }
            if (result is Result.Failed) _error.value = _error.value?.copy(
                show = true,
                message = "Dile a Rocío que esto ha petao por: ${result.exception.localizedMessage}"
            )
        }
    }

    private fun getResponses() {
        launch {
            val result = withContext(Dispatchers.IO) {
                gameManager.getPlayingCards()
            }
            when (result) {
                is Result.Success -> {
                    _responses.value = result.value.map { getWhiteCard(it.cards) }.shuffled()
                }
                is Result.Failed -> _error.value = _error.value?.copy(
                    show = true,
                    message = "Dile a Rocío que esto ha petao por: ${result.exception.localizedMessage}"
                )
            }
        }
    }

    private fun getWhiteCard(cards: List<WhiteCard>): WhiteCard =
        WhiteCard(id = 0, description = cards.map { it.description }.joinToString(separator = " + "))

}

