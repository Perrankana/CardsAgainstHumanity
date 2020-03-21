package com.pandiandcode.cardsagainsthumanity.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pandiandcode.cardsagainsthumanity.domain.Card
import com.pandiandcode.cardsagainsthumanity.domain.usecases.GetBlackCard
import kotlinx.coroutines.*

class MainViewModel(
    private val getBlackCard: GetBlackCard
) : ViewModel(), CoroutineScope by MainScope() {

    private val _blackCardViewModel: MutableLiveData<BlackCardViewModel> = MutableLiveData(
        BlackCardViewModel(visible = false) { onBlackCardClose() }
    )

    val blackCardViewModel: LiveData<BlackCardViewModel> = _blackCardViewModel

    val onBlackDeckClick: () -> Unit = {
        onBlackDeck()
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
}

data class BlackCardViewModel(
    val card: Card? = null,
    val visible: Boolean,
    val onBlackDeckClose: () -> Unit
){
    fun showDraw() = card?.draw?.let { it > 0 } ?: false
    fun showPick() = card?.pick?.let { it > 1 } ?: false
}