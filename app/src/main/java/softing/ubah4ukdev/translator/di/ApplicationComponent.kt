package softing.ubah4ukdev.translator.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import softing.ubah4ukdev.translator.AppTranslator
import softing.ubah4ukdev.translator.di.module.*
import softing.ubah4ukdev.translator.domain.scheduler.Schedulers
import javax.inject.Singleton

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.di
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *   Dagger Application Component
 *
 *   2021.09.25
 *
 *   v1.0
 */
@Singleton
@Component(
    modules = [
        YandexApiModule::class,
        InteractorModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        ScreenModule::class,
        AndroidSupportInjectionModule::class]
)
interface ApplicationComponent : AndroidInjector<AppTranslator> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun withContext(context: Context): Builder

        @BindsInstance
        fun withSchedulers(schedulers: Schedulers): Builder

        fun build(): ApplicationComponent
    }
}