package softing.ubah4ukdev.translator.view.main

import io.reactivex.Observable
import softing.ubah4ukdev.translator.domain.model.AppState
import softing.ubah4ukdev.translator.domain.model.DictionaryResult
import softing.ubah4ukdev.translator.domain.repository.IRepository
import softing.ubah4ukdev.translator.presenter.IInteractor

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
 *   2021.09.19
 *
 *   v1.0
 */
class MainInteractor(
    private val repository: IRepository<DictionaryResult>,
) : IInteractor<AppState> {
    override fun getData(word: String): Observable<AppState> =
        repository.getData(word)
            .map { AppState.Success(it) }
}