package softing.ubah4ukdev.translator.di.module

import dagger.Module
import dagger.Provides
import softing.ubah4ukdev.translator.di.Qualifiers.Local
import softing.ubah4ukdev.translator.di.Qualifiers.Remote
import softing.ubah4ukdev.translator.domain.model.DictionaryResult
import softing.ubah4ukdev.translator.domain.repository.IRepository
import softing.ubah4ukdev.translator.view.main.MainInteractor

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.di
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *   Dagger модуль для Интеракторов
 *
 *   2021.09.25
 *
 *   v1.0
 */
@Module
class InteractorModule {

    @Provides
    internal fun provideMainInteractor(
        @Remote repositoryRemote: IRepository<DictionaryResult>,
        @Local repositoryLocal: IRepository<DictionaryResult>
    ) = MainInteractor(repositoryRemote, repositoryLocal)
}