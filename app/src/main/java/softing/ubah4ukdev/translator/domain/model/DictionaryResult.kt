package softing.ubah4ukdev.translator.domain.model

import com.google.gson.annotations.SerializedName
import softing.ubah4ukdev.translator.domain.model.ModelConstants.MODEL_DICTIONARY_ENTRY_LIST

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.domain.model
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *   Модель, которая приходит в ответе с API
 *
 *   2021.09.19
 *
 *   v1.0
 */
data class DictionaryResult(

    @SerializedName(MODEL_DICTIONARY_ENTRY_LIST)
    val dictionaryEntryList: List<DictionaryEntry>
)