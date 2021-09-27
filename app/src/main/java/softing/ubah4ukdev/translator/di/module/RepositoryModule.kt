package softing.ubah4ukdev.translator.di.module

import dagger.Module
import dagger.Provides
import softing.ubah4ukdev.translator.di.Qualifiers.Local
import softing.ubah4ukdev.translator.di.Qualifiers.Remote
import softing.ubah4ukdev.translator.domain.api.YandexApi
import softing.ubah4ukdev.translator.domain.model.DictionaryResult
import softing.ubah4ukdev.translator.domain.repository.IRepository
import softing.ubah4ukdev.translator.domain.repository.RepositoryImpl
import softing.ubah4ukdev.translator.domain.repository.datasource.CacheDataSourceImpl
import softing.ubah4ukdev.translator.domain.repository.datasource.IDataSource
import softing.ubah4ukdev.translator.domain.repository.datasource.NetworkDataSourceImpl
import javax.inject.Singleton

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.di
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *   Dagger модуль для репозиториев и источников данных
 *
 *   2021.09.25
 *
 *   v1.0
 */
@Module
class RepositoryModule {

    @Provides
    @Singleton
    @Remote
    internal fun provideRepositoryRemote(@Remote dataSourceRemote: IDataSource<DictionaryResult>): IRepository<DictionaryResult> =
        RepositoryImpl(dataSourceRemote)

    @Provides
    @Singleton
    @Local
    internal fun provideRepositoryLocal(@Local dataSourceLocal: IDataSource<DictionaryResult>): IRepository<DictionaryResult> =
        RepositoryImpl(dataSourceLocal)

    @Provides
    @Singleton
    @Remote
    internal fun provideDataSourceRemote(yandexApi: YandexApi): IDataSource<DictionaryResult> =
        NetworkDataSourceImpl(yandexApi)

    @Provides
    @Singleton
    @Local
    internal fun provideDataSourceLocal(): IDataSource<DictionaryResult> = CacheDataSourceImpl()
}