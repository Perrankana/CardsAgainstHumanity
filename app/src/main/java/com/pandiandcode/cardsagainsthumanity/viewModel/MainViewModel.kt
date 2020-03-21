package com.pandiandcode.cardsagainsthumanity.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pandiandcode.cardsagainsthumanity.domain.Card
import com.pandiandcode.cardsagainsthumanity.domain.WhiteDeckRepository
import com.pandiandcode.cardsagainsthumanity.domain.usecases.GetBlackCard
import kotlinx.coroutines.*

class MainViewModel(
    private val getBlackCard: GetBlackCard,
    private val whiteDeckRepository: WhiteDeckRepository
) : ViewModel(), CoroutineScope by MainScope() {

    private val _blackCardViewModel: MutableLiveData<BlackCardViewModel> = MutableLiveData(
        BlackCardViewModel(visible = false) { onBlackCardClose() }
    )
    private val _whiteCards: MutableLiveData<List<Card>> = MutableLiveData(emptyList())

    val blackCardViewModel: LiveData<BlackCardViewModel> = _blackCardViewModel
    val whiteCards: LiveData<List<Card>> = _whiteCards

    val onBlackDeckClick: () -> Unit = {
        onBlackDeck()
    }

    val onWhiteDeckClick: () -> Unit = {
        onWhiteDeck()
    }

    val onWhiteCardClick: (String) -> Unit = {
        onWhiteCard(it)
    }

    private fun onWhiteCard(id: String) {
        _whiteCards.value = _whiteCards.value?.filterNot {
            it.description == id
        }
    }

    private fun onBlackCardClose() {
        _blackCardViewModel.value = _blackCardViewModel.value?.copy(card= null, visible = false)
    }

    private fun onBlackDeck() {
        launch {
            val card = withContext(Dispatchers.IO) {
                getBlackCard()
            }
            _blackCardViewModel.value = _blackCardViewModel.value?.copy(card= card, visible = true)
        }
    }

    private fun onWhiteDeck() {
        launch {
            val whiteCardsList = withContext(Dispatchers.IO) {
                whiteDeckRepository.load()
            }.shuffled()
            val cardsToPick = 10 - (_whiteCards.value?.size ?: 0)
            _whiteCards.value = whiteCardsList.subList(0, cardsToPick) + (_whiteCards.value ?: emptyList())
        }
    }
}