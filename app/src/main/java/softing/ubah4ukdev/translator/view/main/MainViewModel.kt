package softing.ubah4ukdev.translator.view.main

import androidx.lifecycle.LiveData
import io.reactivex.observers.DisposableObserver
import softing.ubah4ukdev.translator.domain.model.AppState
import softing.ubah4ukdev.translator.domain.scheduler.Schedulers
import softing.ubah4ukdev.translator.viewmodel.BaseViewModel
import javax.inject.Inject

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
class MainViewModel @Inject constructor(
    private val interactor: MainInteractor,
    private val schedulers: Schedulers
) : BaseViewModel<AppState>() {

    private var appState: AppState? = null

    fun translateLiveData(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String): LiveData<AppState> {
        compositeDisposable.add(
            interactor
                .getData(word, true)
                .subscribeOn(schedulers.background())
                .observeOn(schedulers.main())
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