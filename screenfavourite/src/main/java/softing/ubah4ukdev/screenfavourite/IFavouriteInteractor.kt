package softing.ubah4ukdev.screenfavourite

import softing.ubah4ukdev.domain.storage.entity.WordFavourite

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.viewmodel
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *
 *
 *   2021.09.25
 *
 *   v1.0
 */
interface IFavouriteInteractor<T> {

    suspend fun getData(): List<WordFavourite>

    suspend fun clearFavourite(): Int
}