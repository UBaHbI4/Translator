package softing.ubah4ukdev.translator.view.main.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import softing.ubah4ukdev.translator.databinding.RecyclerViewItemBinding
import softing.ubah4ukdev.translator.domain.model.DictionaryEntry
import softing.ubah4ukdev.translator.extensions.click

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.view.main.adapter
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *   Вьюхолдер для перевода
 *
 *   2021.09.20
 *
 *   v1.0
 */
class WordViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    private val binding: RecyclerViewItemBinding by viewBinding()

    fun bind(data: DictionaryEntry, delegate: WordAdapter.Delegate?) {
        with(binding) {
            "${data.partOfSpeech} - [${data.transcription}]".also { extensionInfo.text = it }
            headerTextviewRecyclerItem.text = data.text
            descriptionTextviewRecyclerItem.text =
                data.translatesList.joinToString(separator = "\n")
            binding.root.click { delegate?.onItemPicked(data) }
        }
    }
}