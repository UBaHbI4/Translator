package softing.ubah4ukdev.translator.domain.repository.datasource

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.domain.repository.datasource
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *   Фабрика для создания инстанса локального источника данных
 *
 *   2021.09.20
 *
 *   v1.0
 */
object CacheDataSourceFactory {
    fun create(): ICacheDataSource =
        CacheDataSourceImpl()
}