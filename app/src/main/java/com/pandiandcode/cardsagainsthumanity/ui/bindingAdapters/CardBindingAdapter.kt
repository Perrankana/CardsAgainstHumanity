package com.pandiandcode.cardsagainsthumanity.ui.bindingAdapters

import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter

@BindingAdapter("onClickListener")
fun CardView.bindCard(onClickListener: (() -> Unit)) {
    setOnClickListener {
        onClickListener()
    }
}