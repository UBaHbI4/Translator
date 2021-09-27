package softing.ubah4ukdev.translator.domain.model

import com.google.gson.annotations.SerializedName
import softing.ubah4ukdev.translator.domain.model.ModelConstants.MODEL_TEXT
import softing.ubah4ukdev.translator.domain.model.ModelConstants.MODEL_TRANSLATES_LIST

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

    @SerializedName(MODEL_TEXT)
    val text: String,

    @SerializedName(MODEL_TRANSLATES_LIST)
    val translatesList: List<Translation>
) {
    override fun toString(): String {
        return text + " - " + translatesList.joinToString("\n")
    }
}