package softing.ubah4ukdev.translator.domain.model

import com.google.gson.annotations.SerializedName
import softing.ubah4ukdev.translator.domain.model.ModelConstants.MODEL_GENDER
import softing.ubah4ukdev.translator.domain.model.ModelConstants.MODEL_NUM
import softing.ubah4ukdev.translator.domain.model.ModelConstants.MODEL_PART_OF_SPEECH
import softing.ubah4ukdev.translator.domain.model.ModelConstants.MODEL_TEXT
import softing.ubah4ukdev.translator.domain.model.ModelConstants.MODEL_TRANSCRIPTION
import softing.ubah4ukdev.translator.domain.model.ModelConstants.MODEL_TRANSLATES_LIST

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

    @SerializedName(MODEL_GENDER)
    val gender: String = "",

    @SerializedName(MODEL_PART_OF_SPEECH)
    val partOfSpeech: String = "",

    @SerializedName(MODEL_TEXT)
    val text: String = "",

    @SerializedName(MODEL_TRANSLATES_LIST)
    val translatesList: List<Translation> = listOf(),

    @SerializedName(MODEL_TRANSCRIPTION)
    val transcription: String = "",

    @SerializedName(MODEL_NUM)
    val num: String = ""
)