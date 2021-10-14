package softing.ubah4ukdev.screenhistory.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import softing.ubah4ukdev.domain.storage.entity.WordTranslate
import softing.ubah4ukdev.screenhistory.R

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
class HistoryWordAdapter(private val delegate: Delegate?) :
    RecyclerView.Adapter<HistoryWordViewHolder>() {

    interface Delegate {
        /**
         * Событие наступает при выборе
         * строки перевода.
         * @param word Слово
         */
        fun onItemPicked(word: WordTranslate)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryWordViewHolder {
        return HistoryWordViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.history_item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: HistoryWordViewHolder, position: Int) {
        holder.bind(data.get(position), delegate)
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
            oldItems[oldItemPosition].partOfSpeech == newItems[newItemPosition].partOfSpeech
                    && oldItems[oldItemPosition].word == newItems[newItemPosition].word

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldItems[oldItemPosition] == newItems[newItemPosition]
    }
}