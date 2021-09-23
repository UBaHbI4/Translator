package softing.ubah4ukdev.translator.view.main

import androidx.lifecycle.LiveData
import io.reactivex.observers.DisposableObserver
import softing.ubah4ukdev.translator.domain.model.AppState
import softing.ubah4ukdev.translator.domain.repository.RepositoryImpl
import softing.ubah4ukdev.translator.domain.repository.datasource.CacheDataSourceFactory
import softing.ubah4ukdev.translator.domain.repository.datasource.NetworkDataSourceFactory
import softing.ubah4ukdev.translator.viewmodel.BaseViewModel

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.view.main
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *
 *
 *   2021.09.23
 *
 *   v1.0
 */
class MainViewModel(
    private val interactor: MainInteractor = MainInteractor(
        RepositoryImpl(
            NetworkDataSourceFactory.create(),
            CacheDataSourceFactory.create()
        )
    )
) : BaseViewModel<AppState>() {
    private var appState: AppState? = null

    override fun getData(word: String): LiveData<AppState> {
        compositeDisposable.add(
            interactor.getData(word)
                .subscribeOn(schedulerProvider.background())
                .observeOn(schedulerProvider.main())
                .doOnSubscribe { liveDataForViewToObserve.postValue(AppState.Loading(null)) }
                .subscribeWith(getObserver())
        )
        return super.getData(word)
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {
            override fun onNext(state: AppState) {
                appState = state
                liveDataForViewToObserve.postValue(state)
            }

            override fun onError(e: Throwable) {
                liveDataForViewToObserve.postValue(AppState.Error(e))
            }

            override fun onComplete() {
            }
        }
    }
}