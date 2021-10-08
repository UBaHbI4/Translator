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
 *   Значения
 *
 *   2021.09.19
 *
 *   v1.0
 */
data class Mean(

    @SerializedName(ModelConstants.MODEL_TEXT)
    val text: String,

    @SerializedName(ModelConstants.MODEL_GENDER)
    val gender: String = "",

    @SerializedName(ModelConstants.MODEL_PART_OF_SPEECH)
    val partOfSpeech: String = "",

    @SerializedName(ModelConstants.MODEL_NUM)
    val num: String = ""
)