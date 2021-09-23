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
 *   Интерфейс для локального источника данных, например БД
 *
 *   2021.09.20
 *
 *   v1.0
 */
interface ICacheDataSource : IDataSource<DictionaryResult> {
}