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
 *   Синонимы
 *
 *   2021.09.19
 *
 *   v1.0
 */
data class Synonym(
    /**
     *  ?
     */
    @SerializedName("fr")
    val fr: Int,

    /**
     *  Род существительного
     */
    @SerializedName("gen")
    val gen: String,

    /**
     *  Часть речи
     */
    @SerializedName("pos")
    val pos: String,

    /**
     *  Текст статьи, перевода или синонима.
     */
    @SerializedName("text")
    val text: String, // словечко

    /**
     *  Число
     */
    @SerializedName("num")
    val num: String = ""
)