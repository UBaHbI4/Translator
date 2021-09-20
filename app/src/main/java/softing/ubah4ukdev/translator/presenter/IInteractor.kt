package softing.ubah4ukdev.translator.presenter

import io.reactivex.Observable

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.presenter
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *   Интерфейс интерактора
 *
 *   2021.09.19
 *
 *   v1.0
 */
interface IInteractor<T> {
    /**
     * Получить перевод
     * @param word Слово, которое необходимо перевести
     * @return Observable<T>
     */
    fun getData(word: String): Observable<T>
}