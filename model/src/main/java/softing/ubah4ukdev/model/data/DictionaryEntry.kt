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
 *   Словарная статья. В атрибуте ts может указываться транскрипция искомого слова.
 *
 *   2021.09.19
 *
 *   v1.0
 */
data class DictionaryEntry(

    @SerializedName(ModelConstants.MODEL_GENDER)
    val gender: String = "",

    @SerializedName(ModelConstants.MODEL_PART_OF_SPEECH)
    val partOfSpeech: String = "",

    @SerializedName(ModelConstants.MODEL_TEXT)
    val text: String = "",

    @SerializedName(ModelConstants.MODEL_TRANSLATES_LIST)
    val translatesList: List<Translation> = listOf(),

    @SerializedName(ModelConstants.MODEL_TRANSCRIPTION)
    val transcription: String = "",

    @SerializedName(ModelConstants.MODEL_NUM)
    val num: String = ""
)