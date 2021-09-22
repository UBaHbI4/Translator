package softing.ubah4ukdev.translator.domain.repository.datasource

import io.reactivex.Observable
import softing.ubah4ukdev.translator.domain.model.DictionaryResult

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.domain.repository.datasource
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *   Реализация интерфейса локального источника данных.
 *   Пока не реализована.
 *
 *   2021.09.20
 *
 *   v1.0
 */
class CacheDataSourceImpl : ICacheDataSource {
    override fun getData(word: String): Observable<DictionaryResult> =
        Observable.empty()
}