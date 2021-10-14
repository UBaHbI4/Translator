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
 *   Синонимы
 *
 *   2021.09.19
 *
 *   v1.0
 */
data class Synonym(

    @SerializedName(ModelConstants.MODEL_FR)
    val fr: Int,

    @SerializedName(ModelConstants.MODEL_GENDER)
    val gen: String,

    @SerializedName(ModelConstants.MODEL_PART_OF_SPEECH)
    val pos: String,

    @SerializedName(ModelConstants.MODEL_TEXT)
    val text: String,

    @SerializedName(ModelConstants.MODEL_NUM)
    val num: String = ""
)