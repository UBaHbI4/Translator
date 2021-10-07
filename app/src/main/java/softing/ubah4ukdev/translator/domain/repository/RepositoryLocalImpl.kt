package softing.ubah4ukdev.translator.domain.repository

import softing.ubah4ukdev.translator.domain.repository.datasource.IDataSourceLocal
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
class RepositoryLocalImpl(private val dataSource: IDataSourceLocal) : IRepositoryLocal {
    override suspend fun saveToDb(word: WordTranslate) {
        dataSource.saveToDB(word)
    }

    override suspend fun saveToDb(words: List<WordTranslate>) {
        dataSource.saveToDB(words)
    }

    override suspend fun fetchHistory(): List<WordTranslate> =
        dataSource.fetchHistory()

    override suspend fun findInHistoryByWord(word: String): WordTranslate =
        dataSource.findInHistoryByWord(word)

    override suspend fun fetchFavourite(): List<WordFavourite> =
        dataSource.fetchFavourite()

    override suspend fun insertWordToFavourite(word: WordFavourite): Long =
        dataSource.insertWordToFavourite(word)

    override suspend fun clearFavourite(): Int =
        dataSource.clearFavourite()

    override suspend fun clearHistory(): Int =
        dataSource.clearHistory()
}