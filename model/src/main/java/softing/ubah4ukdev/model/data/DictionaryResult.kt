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
 *   Модель, которая приходит в ответе с API
 *
 *   2021.09.19
 *
 *   v1.0
 */
data class DictionaryResult(

    @SerializedName(ModelConstants.MODEL_DICTIONARY_ENTRY_LIST)
    val dictionaryEntryList: List<DictionaryEntry>
)