package softing.ubah4ukdev.translator.domain.repository.datasource

import io.reactivex.Observable
import softing.ubah4ukdev.translator.domain.api.YandexApi
import softing.ubah4ukdev.translator.domain.model.DictionaryResult

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.domain.repository.datasource
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *   Реализация интерфейса удаленного источника данных
 *
 *   2021.09.20
 *
 *   v1.0
 */
class NetworkDataSourceImpl(private val yandexApi: YandexApi) :
    IDataSource<DictionaryResult> {

    override fun getData(word: String): Observable<DictionaryResult> =
        yandexApi.search(word)
}