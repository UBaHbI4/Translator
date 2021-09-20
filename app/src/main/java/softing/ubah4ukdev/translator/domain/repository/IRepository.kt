package softing.ubah4ukdev.translator.domain.repository

import io.reactivex.Observable

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.domain.repository
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *   Интерфейс репозитория с описанием методов получения данных
 *
 *   2021.09.19
 *
 *   v1.0
 */
interface IRepository<T> {
    /**
     * Получить перевод
     * @param word Слово, которое необходимо перевести
     * @return Observable<T>
     */
    fun getData(word: String): Observable<T>
}