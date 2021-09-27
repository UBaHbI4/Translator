package softing.ubah4ukdev.translator.domain.repository.datasource

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

    override suspend fun getData(word: String): DictionaryResult =
        yandexApi.searchAsync(word).await()
}