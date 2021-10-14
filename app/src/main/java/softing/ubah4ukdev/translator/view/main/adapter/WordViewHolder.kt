package softing.ubah4ukdev.translator.view.main.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import softing.ubah4ukdev.domain.storage.entity.WordTranslate
import softing.ubah4ukdev.translator.databinding.MainItemBinding
import softing.ubah4ukdev.utils.extensions.click

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

    private val binding: MainItemBinding by viewBinding()

    fun bind(data: WordTranslate, delegate: WordAdapter.Delegate?) {
        with(binding) {
            header.text = data.word
            description.text = data.translate
            binding.root.click { delegate?.onItemPicked(data) }
            binding.favourite.click { delegate?.onFavouritePicked(data) }
        }
    }
}