package softing.ubah4ukdev.translator.view.history.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import softing.ubah4ukdev.translator.databinding.HistoryItemBinding
import softing.ubah4ukdev.translator.domain.storage.entity.WordTranslate
import softing.ubah4ukdev.translator.extensions.click

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
class HistoryWordViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    private val binding: HistoryItemBinding by viewBinding()

    fun bind(data: WordTranslate, delegate: HistoryWordAdapter.Delegate?) {
        with(binding) {
            header.text = data.word
            description.text = data.translate
            binding.root.click { delegate?.onItemPicked(data) }
        }
    }
}