package softing.ubah4ukdev.translator.domain.model

import com.google.gson.annotations.SerializedName

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.domain.model
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *   Примеры переводов
 *
 *   2021.09.19
 *
 *   v1.0
 */
data class Examples(
    /**
     *  Текст статьи, перевода или синонима.
     */
    @SerializedName("text")
    val text: String,

    /**
     *  Список переводов
     */
    @SerializedName("tr")
    val translatesList: List<Translation>
) {
    override fun toString(): String {
        return text + " - " + translatesList.joinToString("\n")
    }
}