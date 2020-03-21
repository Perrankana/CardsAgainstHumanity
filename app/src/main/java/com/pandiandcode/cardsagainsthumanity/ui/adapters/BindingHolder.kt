package com.pandiandcode.cardsagainsthumanity.ui.adapters

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BindingHolder(rowView: View) : RecyclerView.ViewHolder(rowView) {

    val binding: ViewDataBinding? by lazyOf(DataBindingUtil.bind(rowView))
}