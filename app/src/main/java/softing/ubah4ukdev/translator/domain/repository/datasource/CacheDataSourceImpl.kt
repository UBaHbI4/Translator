package softing.ubah4ukdev.translator.domain.repository.datasource

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
class CacheDataSourceImpl : IDataSource<DictionaryResult> {
    companion object {
        private const val ERROR_MESSAGE =
            "Локальный источник данных еще не реализован.\nПопросите разработчика реализовать :)"
    }

    override suspend fun getData(word: String): DictionaryResult =
        throw Exception(ERROR_MESSAGE)
}