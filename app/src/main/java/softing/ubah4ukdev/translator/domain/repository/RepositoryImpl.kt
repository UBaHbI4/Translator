package softing.ubah4ukdev.translator.domain.repository

import softing.ubah4ukdev.translator.domain.model.DictionaryResult
import softing.ubah4ukdev.translator.domain.repository.datasource.IDataSource

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.domain.repository
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