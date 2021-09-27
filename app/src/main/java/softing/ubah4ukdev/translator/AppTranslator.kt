package softing.ubah4ukdev.translator

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import softing.ubah4ukdev.translator.di.DaggerApplicationComponent
import softing.ubah4ukdev.translator.domain.scheduler.DefaultSchedulers

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
class AppTranslator : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<AppTranslator> =
        DaggerApplicationComponent
            .builder()
            .withContext(applicationContext)
            .withSchedulers(DefaultSchedulers())
            .build()
}