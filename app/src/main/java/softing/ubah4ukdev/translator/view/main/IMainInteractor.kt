package softing.ubah4ukdev.translator.view.main

import softing.ubah4ukdev.translator.domain.storage.entity.WordFavourite
import softing.ubah4ukdev.translator.domain.storage.entity.WordTranslate

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
interface IMainInteractor<T> {

    suspend fun getData(word: String, fromRemoteSource: Boolean): T
    suspend fun saveToDb(word: WordTranslate)
    suspend fun saveToDb(words: List<WordTranslate>)
    suspend fun insertWordToFavourite(word: WordFavourite): Long
}