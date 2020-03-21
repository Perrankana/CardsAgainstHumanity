package com.pandiandcode.cardsagainsthumanity.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pandiandcode.cardsagainsthumanity.R
import com.pandiandcode.cardsagainsthumanity.BR
import com.pandiandcode.cardsagainsthumanity.domain.Card

class WhiteCardsAdapter : RecyclerView.Adapter<BindingHolder>() {

    var onClickListener: (String) -> Unit = {}

    private val whiteCards: MutableList<Card> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder =
        BindingHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_item_layout, parent, false)
        )

    override fun getItemCount(): Int = whiteCards.size

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        holder.binding?.setVariable(BR.card, whiteCards[position])
        holder.binding?.executePendingBindings()
        holder.binding?.root?.setOnClickListener {
            onClickListener(whiteCards[position].description)
        }
    }

    fun update(whiteCardsList: List<Card>) {
        val diffCallback = CardsDiffCallback(whiteCards, whiteCardsList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        whiteCards.clear()
        whiteCards.addAll(whiteCardsList)
        diffResult.dispatchUpdatesTo(this)
    }
}

class CardsDiffCallback(private val oldList: List<Card>, private val newList: List<Card>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].description === newList[newItemPosition].description
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        val (_, value, name) = oldList[oldPosition]
        val (_, value1, name1) = newList[newPosition]

        return name == name1 && value == value1
    }
}