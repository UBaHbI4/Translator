package softing.ubah4ukdev.translator.domain.repository

import io.reactivex.Observable
import softing.ubah4ukdev.translator.domain.model.DictionaryResult
import softing.ubah4ukdev.translator.domain.repository.datasource.ICacheDataSource
import softing.ubah4ukdev.translator.domain.repository.datasource.INetworkDataSource
import java.util.concurrent.TimeUnit

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
    private val cloud: INetworkDataSource,
    private val cache: ICacheDataSource
) : IRepository<DictionaryResult> {

    override fun getData(word: String): Observable<DictionaryResult> =
        cloud.getData(word)
}