package softing.ubah4ukdev.translator.view.main

import android.util.Log
import androidx.lifecycle.LiveData
import io.reactivex.observers.DisposableObserver
import io.reactivex.rxkotlin.plusAssign
import softing.ubah4ukdev.translator.domain.model.AppState
import softing.ubah4ukdev.translator.domain.scheduler.Schedulers
import softing.ubah4ukdev.translator.utils.network.NetworkState
import softing.ubah4ukdev.translator.utils.network.NetworkStateObservable
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
    private val schedulers: Schedulers,
    private val networkState: NetworkStateObservable
) : BaseViewModel<AppState>() {

    private var appState: AppState? = null

    fun translateLiveData(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    fun networkStateLiveData(): LiveData<Boolean> {
        return liveDataForNetworkState
    }

    override fun getData(word: String): LiveData<AppState> {
        compositeDisposable +=
            interactor
                .getData(word, true)
                .subscribeOn(schedulers.background())
                .observeOn(schedulers.main())
                .doOnSubscribe { liveDataForViewToObserve.postValue(AppState.Loading(null)) }
                .subscribeWith(getObserver())
        return super.getData(word)
    }

    override fun getNetworkState(): LiveData<Boolean> {
        compositeDisposable +=
            networkState
                .doOnNext { state ->
                    liveDataForNetworkState.postValue(state == NetworkState.CONNECTED)
                    Log.d("networkState", "publish")
                }
                .publish()
                .connect()

        return super.getNetworkState()
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