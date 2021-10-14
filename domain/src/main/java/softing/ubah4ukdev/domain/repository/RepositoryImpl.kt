package softing.ubah4ukdev.domain.repository

import softing.ubah4ukdev.domain.repository.datasource.IDataSource
import softing.ubah4ukdev.model.data.DictionaryResult

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.repository.domain.repository
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *   Реализация интерфейса IRepository
 *   cache пока не используется.
 *
 *   2021.09.20
 *
 *   v1.0
 */
class RepositoryImpl(
    private val dataSource: IDataSource<DictionaryResult>
) : IRepository<DictionaryResult> {

    override suspend fun getData(word: String): DictionaryResult =
        dataSource.getData(word)
}