package softing.ubah4ukdev.translator.viewmodel

import io.reactivex.Observable

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
interface IInteractor<T> {

    fun getData(word: String, fromRemoteSource: Boolean): Observable<T>
}
