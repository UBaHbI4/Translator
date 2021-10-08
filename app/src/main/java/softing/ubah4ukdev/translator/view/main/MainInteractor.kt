package softing.ubah4ukdev.translator.view.main

import softing.ubah4ukdev.translator.domain.model.DictionaryResult
import softing.ubah4ukdev.translator.domain.repository.IRepository
import softing.ubah4ukdev.translator.domain.repository.IRepositoryLocal
import softing.ubah4ukdev.translator.domain.storage.entity.WordFavourite
import softing.ubah4ukdev.translator.domain.storage.entity.WordTranslate

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
class MainInteractor(
    val repositoryRemote: IRepository<DictionaryResult>,
    val repositoryLocal: IRepositoryLocal
) : IMainInteractor<DictionaryResult> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): DictionaryResult =
        repositoryRemote
            .getData(word)

    override suspend fun saveToDb(word: WordTranslate) {
        repositoryLocal.saveToDb(word)
    }

    override suspend fun saveToDb(words: List<WordTranslate>) {
        repositoryLocal.saveToDb(words)
    }

    override suspend fun insertWordToFavourite(word: WordFavourite) =
        repositoryLocal.insertWordToFavourite(word)
}