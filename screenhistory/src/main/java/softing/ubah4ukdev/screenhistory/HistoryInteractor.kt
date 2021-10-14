package softing.ubah4ukdev.screenhistory

import softing.ubah4ukdev.domain.repository.IRepositoryLocal
import softing.ubah4ukdev.domain.storage.entity.WordTranslate

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.view.main
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
class HistoryInteractor(
    val repositoryLocal: IRepositoryLocal
) : IHistoryInteractor<List<WordTranslate>> {

    override suspend fun getData(): List<WordTranslate> {
        return repositoryLocal
            .fetchHistory()
    }

    override suspend fun saveToDb(word: WordTranslate) {
        repositoryLocal.saveToDb(word)
    }

    override suspend fun saveToDb(words: List<WordTranslate>) {
        repositoryLocal.saveToDb(words)
    }

    override suspend fun clearHistory(): Int =
        repositoryLocal.clearHistory()
}