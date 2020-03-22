package com.pandiandcode.cardsagainsthumanity.ui.bindingAdapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pandiandcode.cardsagainsthumanity.domain.model.WhiteCard
import com.pandiandcode.cardsagainsthumanity.ui.adapters.WhiteCardsAdapter

@BindingAdapter("whiteCards", "onClickListener")
fun RecyclerView.bindCards(whiteCardsList: List<WhiteCard>, onClickListener: ((Int) -> Unit)) {
    adapter?.apply {
        (this as? WhiteCardsAdapter)?.update(whiteCardsList)
    } ?: run {
        adapter = WhiteCardsAdapter().apply {
            update(whiteCardsList)
            this.onClickListener = onClickListener
        }
    }
}