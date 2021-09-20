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
 *   Словарная статья. В атрибуте ts может указываться транскрипция искомого слова.
 *
 *   2021.09.19
 *
 *   v1.0
 */
data class DictionaryEntry(
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
     *  Текст статьи, перевода или синонима.
     */
    @SerializedName("text")
    val text: String = "",

    /**
     *  Список переводов
     */
    @SerializedName("tr")
    val translatesList: List<Translation> = listOf(),

    /**
     *  Транскрипция искомого слова
     */
    @SerializedName("ts")
    val transcription: String = "",

    /**
     *  Число
     */
    @SerializedName("num")
    val num: String = ""
)