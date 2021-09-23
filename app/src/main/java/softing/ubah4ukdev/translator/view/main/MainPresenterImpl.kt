package softing.ubah4ukdev.translator.view.main

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import softing.ubah4ukdev.translator.domain.model.AppState
import softing.ubah4ukdev.translator.domain.repository.RepositoryFactory
import softing.ubah4ukdev.translator.domain.scheduler.SchedulerFactory
import softing.ubah4ukdev.translator.domain.scheduler.Schedulers
import softing.ubah4ukdev.translator.presenter.IPresenter
import softing.ubah4ukdev.translator.view.base.IView

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.view.main
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *   Реализация интерфейса IPresenter
 *
 *   2021.09.19
 *
 *   v1.0
 */
class MainPresenterImpl<T : AppState, V : IView>(
    private val interactor: MainInteractor = MainInteractor(RepositoryFactory.create()),
    private val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    private val scheduler: Schedulers = SchedulerFactory.create()
) : IPresenter<T, V> {

    private var currentView: V? = null

    override fun attachView(view: V) {
        if (view != currentView) {
            currentView = view
        }
    }

    override fun detachView(view: V) {
        compositeDisposable.clear()
        if (view == currentView) {
            currentView = null
        }
    }

    override fun getData(word: String) {
        compositeDisposable.add(
            interactor.getData(word)
                .subscribeOn(scheduler.background())
                .observeOn(scheduler.main())
                .doOnSubscribe { currentView?.renderData(AppState.Loading(null)) }
                .subscribeWith(getObserver())
        )
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {

            override fun onNext(appState: AppState) {
                currentView?.renderData(appState)
            }

            override fun onError(e: Throwable) {
                currentView?.renderData(AppState.Error(e))
            }

            override fun onComplete() {
            }
        }
    }
}