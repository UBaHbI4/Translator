package softing.ubah4ukdev.translator.domain.model

import com.google.gson.annotations.SerializedName
import softing.ubah4ukdev.translator.domain.model.ModelConstants.MODEL_ASP
import softing.ubah4ukdev.translator.domain.model.ModelConstants.MODEL_EXAMPLE_LIST
import softing.ubah4ukdev.translator.domain.model.ModelConstants.MODEL_FR
import softing.ubah4ukdev.translator.domain.model.ModelConstants.MODEL_GENDER
import softing.ubah4ukdev.translator.domain.model.ModelConstants.MODEL_MEAN_LIST
import softing.ubah4ukdev.translator.domain.model.ModelConstants.MODEL_NUM
import softing.ubah4ukdev.translator.domain.model.ModelConstants.MODEL_PART_OF_SPEECH
import softing.ubah4ukdev.translator.domain.model.ModelConstants.MODEL_SYNONYM_LIST
import softing.ubah4ukdev.translator.domain.model.ModelConstants.MODEL_TEXT

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.domain.model
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *   Перевод
 *
 *   2021.09.19
 *
 *   v1.0
 */
data class Translation(

    @SerializedName(MODEL_ASP)
    val asp: String?,

    @SerializedName(MODEL_EXAMPLE_LIST)
    val examplesList: List<Examples>?,

    @SerializedName(MODEL_FR)
    val fr: Int,

    @SerializedName(MODEL_GENDER)
    val gen: String?,

    @SerializedName(MODEL_MEAN_LIST)
    val meanList: List<Mean> = listOf(),

    @SerializedName(MODEL_NUM)
    val num: String?,

    @SerializedName(MODEL_PART_OF_SPEECH)
    val pos: String = "",

    @SerializedName(MODEL_SYNONYM_LIST)
    val synonymList: List<Synonym> = listOf(),

    @SerializedName(MODEL_TEXT)
    val text: String = ""
) {
    override fun toString(): String {

        var result: String = this.text

        this.num?.let {
            result += ", ${this.num} ч."
        }

        this.gen?.let {
            result += ", ${this.gen} род."
        }

        this.asp?.let {
            result += ", ${this.asp}"
        }

        examplesList?.let {
            result += "\n[" + it.joinToString(separator = ",\n") + "]"
        }

        return result
    }
}