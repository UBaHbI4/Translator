package softing.ubah4ukdev.domain.repository.datasource

import softing.ubah4ukdev.domain.storage.WordStorage
import softing.ubah4ukdev.domain.storage.entity.WordFavourite
import softing.ubah4ukdev.domain.storage.entity.WordTranslate

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.repository.domain.repository.datasource
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *   Реализация интерфейса локального источника данных.
 *   Пока не реализована.
 *
 *   2021.09.20
 *
 *   v1.0
 */
class CacheDataSourceImpl(private val wordStorage: WordStorage) : IDataSourceLocal {

    override suspend fun saveToDB(word: WordTranslate) {
        wordStorage
            .wordDao()
            .insertWordToHistory(word)
    }

    override suspend fun saveToDB(words: List<WordTranslate>) {
        wordStorage
            .wordDao()
            .insertWordsToHistory(words)
    }

    override suspend fun fetchHistory(): List<WordTranslate> =
        wordStorage
            .wordDao()
            .fetchHistory()

    override suspend fun findInHistoryByWord(word: String): WordTranslate =
        wordStorage
            .wordDao()
            .findInHistoryByWord(word)

    override suspend fun fetchFavourite(): List<WordFavourite> =
        wordStorage
            .wordDao()
            .fetchFavourite()

    override suspend fun insertWordToFavourite(word: WordFavourite): Long =
        wordStorage
            .wordDao()
            .insertWordToFavourite(word)

    override suspend fun clearFavourite(): Int =
        wordStorage
            .wordDao()
            .clearFavourite()

    override suspend fun clearHistory(): Int =
        wordStorage
            .wordDao()
            .clearHistory()
}