package softing.ubah4ukdev.translator.view.base

import androidx.appcompat.app.AppCompatActivity
import softing.ubah4ukdev.translator.R
import softing.ubah4ukdev.translator.domain.model.AppState
import softing.ubah4ukdev.translator.viewmodel.BaseViewModel

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
abstract class BaseActivity<T : AppState> : AppCompatActivity(R.layout.activity_main) {

    abstract val model: BaseViewModel<T>

    abstract fun renderData(appState: T)
}