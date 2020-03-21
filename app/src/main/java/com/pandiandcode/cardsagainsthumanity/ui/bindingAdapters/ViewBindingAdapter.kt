package com.pandiandcode.cardsagainsthumanity.ui.bindingAdapters

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("isVisible")
fun View.bindVisibility(visible: Boolean){
    visibility = if (visible) View.VISIBLE else View.GONE
}