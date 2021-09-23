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
 *   Модель, которая приходит в ответе с API
 *
 *   2021.09.19
 *
 *   v1.0
 */
data class DictionaryResult(
    /**
     *  Список словарных статей
     */
    @SerializedName("def")
    val dictionaryEntryList: List<DictionaryEntry>
)