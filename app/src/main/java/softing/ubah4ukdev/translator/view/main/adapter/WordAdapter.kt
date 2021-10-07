package softing.ubah4ukdev.translator.view.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import softing.ubah4ukdev.translator.R
import softing.ubah4ukdev.translator.domain.storage.entity.WordTranslate

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.view.main.adapter
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *   Адаптер для списка переводов
 *
 *   2021.09.19
 *
 *   v1.0
 */
class WordAdapter(
    private val delegate: Delegate?
) : RecyclerView.Adapter<WordViewHolder>() {

    interface Delegate {
        /**
         * Событие наступает при выборе
         * строки перевода.
         * @param word Слово
         */
        fun onItemPicked(word: WordTranslate)

        /**
         * Событие наступает при клике
         * по сердечку для добавления в избранное.
         * @param word Слово
         */
        fun onFavouritePicked(word: WordTranslate)
    }

    private val data = ArrayList<WordTranslate>()

    fun setData(newList: ArrayList<WordTranslate>) {
        val result = DiffUtil.calculateDiff(DiffUtilCallback(this.data, newList))
        result.dispatchUpdatesTo(this)
        this.data.clear()
        this.data.addAll(newList)
    }

    fun clear() {
        this.data.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.main_item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.bind(data[position], delegate)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class DiffUtilCallback(
        private var oldItems: ArrayList<WordTranslate>,
        private var newItems: ArrayList<WordTranslate>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldItems.size

        override fun getNewListSize(): Int = newItems.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition].word == newItems[newItemPosition].word &&
                    oldItems[oldItemPosition].partOfSpeech == newItems[newItemPosition].partOfSpeech

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition] == newItems[newItemPosition]
    }
}