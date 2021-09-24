package softing.ubah4ukdev.translator.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import softing.ubah4ukdev.translator.R
import softing.ubah4ukdev.translator.domain.model.AppState
import softing.ubah4ukdev.translator.viewmodel.BaseViewModel
import softing.ubah4ukdev.translator.viewmodel.IInteractor
import javax.inject.Inject

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.view.base
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *   Общий абстрактный класс для Activity
 *
 *   2021.09.19
 *
 *   v1.0
 */
abstract class BaseActivity<T : AppState, I : IInteractor<T>> :
    AppCompatActivity(R.layout.activity_main),
    HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    abstract val model: BaseViewModel<T>

    abstract fun renderData(appState: T)
}