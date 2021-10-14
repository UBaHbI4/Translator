package softing.ubah4ukdev.screenfavourite.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import softing.ubah4ukdev.domain.storage.entity.WordFavourite
import softing.ubah4ukdev.screenfavourite.R

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.view.history.adapter
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *
 *
 *   2021.10.07
 *
 *   v1.0
 */
class FavouriteWordAdapter(private val delegate: Delegate?) :
    RecyclerView.Adapter<FavouriteWordViewHolder>() {

    interface Delegate {
        /**
         * Событие наступает при выборе
         * строки со словом.
         * @param word Слово
         */
        fun onItemPicked(word: WordFavourite)
    }

    private val data = ArrayList<WordFavourite>()

    fun setData(newList: ArrayList<WordFavourite>) {
        val result = DiffUtil.calculateDiff(DiffUtilCallback(this.data, newList))
        result.dispatchUpdatesTo(this)
        this.data.clear()
        this.data.addAll(newList)
    }

    fun clear() {
        this.data.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteWordViewHolder {
        return FavouriteWordViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.favourite_item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: FavouriteWordViewHolder, position: Int) {
        holder.bind(data.get(position), delegate)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class DiffUtilCallback(
        private var oldItems: ArrayList<WordFavourite>,
        private var newItems: ArrayList<WordFavourite>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldItems.size

        override fun getNewListSize(): Int = newItems.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition].partOfSpeech == newItems[newItemPosition].partOfSpeech
                    && oldItems[oldItemPosition].word == newItems[newItemPosition].word

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition] == newItems[newItemPosition]
    }
}