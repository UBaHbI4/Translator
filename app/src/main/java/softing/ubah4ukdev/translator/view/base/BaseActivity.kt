package softing.ubah4ukdev.translator.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import softing.ubah4ukdev.translator.R
import softing.ubah4ukdev.translator.domain.model.AppState
import softing.ubah4ukdev.translator.presenter.IPresenter

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
abstract class BaseActivity<T : AppState> : AppCompatActivity(R.layout.activity_main), IView {

    protected lateinit var presenter: IPresenter<T, IView>

    protected abstract fun createPresenter(): IPresenter<T, IView>

    abstract override fun renderData(appState: AppState)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }
}