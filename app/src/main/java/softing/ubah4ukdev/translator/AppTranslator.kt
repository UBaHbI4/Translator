package softing.ubah4ukdev.translator

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import softing.ubah4ukdev.translator.di.Di

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *   Приложение "Translator"
 *
 *   2021.09.19
 *
 *   v1.0
 */
class AppTranslator : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppTranslator)
            modules(
                listOf(
                    Di.viewModelModule(),
                    Di.interactorModule(),
                    Di.networkModule(),
                    Di.repositoryModule(),
                    Di.yandexApiModule(),
                    Di.navigationModule()
                )
            )
        }
    }
}