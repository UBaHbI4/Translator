package softing.ubah4ukdev.model.data

import com.google.gson.annotations.SerializedName

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.repository.domain.model
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

    @SerializedName(ModelConstants.MODEL_TEXT)
    val text: String,

    @SerializedName(ModelConstants.MODEL_TRANSLATES_LIST)
    val translatesList: List<Translation>
) {
    override fun toString(): String {
        return text + " - " + translatesList.joinToString("\n")
    }
}