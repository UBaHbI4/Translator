package softing.ubah4ukdev.translator.view.main

import android.util.Log
import io.reactivex.Observable
import softing.ubah4ukdev.translator.domain.model.AppState
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
) : IInteractor<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            Log.d("translatorDebug", "repositoryRemote")
            repositoryRemote
        } else {
            Log.d("translatorDebug", "repositoryLocal")
            repositoryLocal
        }.getData(word).map { AppState.Success(it) }
    }
}