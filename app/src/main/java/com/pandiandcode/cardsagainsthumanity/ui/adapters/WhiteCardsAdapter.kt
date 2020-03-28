package com.pandiandcode.cardsagainsthumanity.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.pandiandcode.cardsagainsthumanity.BR
import com.pandiandcode.cardsagainsthumanity.R
import com.pandiandcode.cardsagainsthumanity.domain.model.WhiteCard

class WhiteCardsAdapter : RecyclerView.Adapter<BindingHolder>() {

    var onClickListener: (WhiteCard) -> Unit = {}

    private val whiteCards: MutableList<WhiteCard> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder =
        BindingHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_item_layout, parent, false)
        ).also { holder ->
            holder.itemView.setOnClickListener {
                onClickListener(whiteCards[holder.adapterPosition])
            }
        }

    override fun getItemCount(): Int = whiteCards.size

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        holder.binding?.setVariable(BR.card, whiteCards[position])
        holder.binding?.executePendingBindings()
    }

    fun update(whiteCardsList: List<WhiteCard>) {
        val diffCallback = CardsDiffCallback(whiteCards, whiteCardsList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        whiteCards.clear()
        whiteCards.addAll(whiteCardsList)
        diffResult.dispatchUpdatesTo(this)
    }
}

class CardsDiffCallback(
    private val oldList: List<WhiteCard>,
    private val newList: List<WhiteCard>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return oldList[oldPosition] == newList[newPosition]
    }
}