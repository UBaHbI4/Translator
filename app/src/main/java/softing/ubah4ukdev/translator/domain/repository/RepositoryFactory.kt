package softing.ubah4ukdev.translator.domain.repository

import softing.ubah4ukdev.translator.domain.model.DictionaryResult
import softing.ubah4ukdev.translator.domain.repository.datasource.CacheDataSourceFactory
import softing.ubah4ukdev.translator.domain.repository.datasource.NetworkDataSourceFactory

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.domain.repository
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *   Фабрика для создания инстанса репозитория
 *
 *   2021.09.20
 *
 *   v1.0
 */
object RepositoryFactory {
    private val repository: IRepository<DictionaryResult> by lazy {
        RepositoryImpl(
            NetworkDataSourceFactory.create(),
            CacheDataSourceFactory.create()
        )
    }

    fun create(): IRepository<DictionaryResult> = repository
}