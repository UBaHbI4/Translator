package softing.ubah4ukdev.translator.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import softing.ubah4ukdev.translator.utils.network.NetworkStateObservable
import javax.inject.Singleton

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.di.module
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *   Dagger модуль для сетевых штук
 *
 *   2021.09.25
 *
 *   v1.0
 */
@Module
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideNetworkState(context: Context) = NetworkStateObservable(context)
}