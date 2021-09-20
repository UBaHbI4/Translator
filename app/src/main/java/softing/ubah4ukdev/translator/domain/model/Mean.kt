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
    /**
     *  Часть речи
     */
    @SerializedName("text")
    val text: String,

    /**
     *  Род существительного
     */
    @SerializedName("gen")
    val gender: String = "",

    /**
     *  Часть речи
     */
    @SerializedName("pos")
    val partOfSpeech: String = "",

    /**
     *  Число
     */
    @SerializedName("num")
    val num: String = ""
)