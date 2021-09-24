package softing.ubah4ukdev.translator.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import softing.ubah4ukdev.translator.di.ViewModelFactory
import softing.ubah4ukdev.translator.di.ViewModelKey
import softing.ubah4ukdev.translator.view.main.MainViewModel

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.di
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *   Dagger модуль для Вью моделей
 *
 *   2021.09.25
 *
 *   v1.0
 */
@Module(includes = [InteractorModule::class])
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    protected abstract fun mainViewModel(mainViewModel: MainViewModel): ViewModel
}