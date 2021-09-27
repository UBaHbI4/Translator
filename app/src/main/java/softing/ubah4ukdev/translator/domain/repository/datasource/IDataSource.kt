package softing.ubah4ukdev.translator.domain.repository.datasource

import io.reactivex.Observable

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.domain.datasource
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *   Общий интерфейс для источника данных
 *
 *   2021.09.19
 *
 *   v1.0
 */
interface IDataSource<T> {

    /**
     * Получить перевод
     * @param word Слово, которое необходимо перевести
     * @return Observable<T>
     */
    fun getData(word: String): Observable<T>
}