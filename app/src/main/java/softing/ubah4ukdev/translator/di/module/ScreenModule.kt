package softing.ubah4ukdev.translator.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import softing.ubah4ukdev.translator.view.main.MainActivity

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.di
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *   Dagger модуль для Activities and Fragments
 *
 *   2021.09.25
 *
 *   v1.0
 */
@Module
interface ScreenModule {

    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity
}