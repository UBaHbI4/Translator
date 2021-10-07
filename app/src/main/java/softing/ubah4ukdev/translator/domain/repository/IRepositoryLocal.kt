package softing.ubah4ukdev.translator.domain.repository

import androidx.room.Query
import softing.ubah4ukdev.translator.domain.storage.entity.WordFavourite
import softing.ubah4ukdev.translator.domain.storage.entity.WordTranslate

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.domain.repository
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *
 *
 *   2021.10.06
 *
 *   v1.0
 */
interface IRepositoryLocal {

    suspend fun saveToDb(word: WordTranslate)

    suspend fun saveToDb(words: List<WordTranslate>)

    suspend fun fetchHistory(): List<WordTranslate>

    suspend fun findInHistoryByWord(word: String): WordTranslate

    suspend fun fetchFavourite(): List<WordFavourite>

    suspend fun insertWordToFavourite(word: WordFavourite): Long

    suspend fun clearFavourite(): Int

    suspend fun clearHistory(): Int
}