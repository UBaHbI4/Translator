package softing.ubah4ukdev.translator.domain.model

import com.google.gson.annotations.SerializedName
import softing.ubah4ukdev.translator.domain.model.ModelConstants.MODEL_FR
import softing.ubah4ukdev.translator.domain.model.ModelConstants.MODEL_GENDER
import softing.ubah4ukdev.translator.domain.model.ModelConstants.MODEL_NUM
import softing.ubah4ukdev.translator.domain.model.ModelConstants.MODEL_PART_OF_SPEECH
import softing.ubah4ukdev.translator.domain.model.ModelConstants.MODEL_TEXT

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.domain.model
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *   Синонимы
 *
 *   2021.09.19
 *
 *   v1.0
 */
data class Synonym(

    @SerializedName(MODEL_FR)
    val fr: Int,

    @SerializedName(MODEL_GENDER)
    val gen: String,

    @SerializedName(MODEL_PART_OF_SPEECH)
    val pos: String,

    @SerializedName(MODEL_TEXT)
    val text: String,

    @SerializedName(MODEL_NUM)
    val num: String = ""
)