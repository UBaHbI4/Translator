package softing.ubah4ukdev.translator.view.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import softing.ubah4ukdev.translator.R
import softing.ubah4ukdev.translator.domain.model.AppState
import softing.ubah4ukdev.translator.domain.model.DictionaryResult
import softing.ubah4ukdev.translator.viewmodel.BaseViewModel
import softing.ubah4ukdev.translator.viewmodel.IInteractor

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
abstract class BaseActivity<T : AppState, I : IInteractor<DictionaryResult>> :
    AppCompatActivity(R.layout.activity_main) {

    protected var isNetworkAvailable: Boolean = false

    abstract val model: BaseViewModel<T>

    abstract fun renderData(appState: T)

    protected fun noInternetMessageShow() {
        Toast.makeText(baseContext, getString(R.string.no_internet_message), Toast.LENGTH_LONG)
            .show()
    }
}