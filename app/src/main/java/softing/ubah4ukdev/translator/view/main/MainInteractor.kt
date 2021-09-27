package softing.ubah4ukdev.translator.view.main

import softing.ubah4ukdev.translator.domain.model.DictionaryResult
import softing.ubah4ukdev.translator.domain.repository.IRepository
import softing.ubah4ukdev.translator.viewmodel.IInteractor

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
    val repositoryLocal: IRepository<DictionaryResult>
) : IInteractor<DictionaryResult> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): DictionaryResult {
        return if (fromRemoteSource) {
            repositoryRemote
        } else {
            repositoryLocal
        }.getData(word)
    }
}