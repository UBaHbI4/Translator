package softing.ubah4ukdev.translator.view.main

import androidx.lifecycle.LiveData
import io.reactivex.rxkotlin.plusAssign
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import softing.ubah4ukdev.translator.domain.model.AppState
import softing.ubah4ukdev.translator.utils.network.NetworkState
import softing.ubah4ukdev.translator.utils.network.NetworkStateObservable
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
class MainViewModel constructor(
    private val interactor: MainInteractor,
    private val networkState: NetworkStateObservable,
) : BaseViewModel<AppState>() {

    companion object {

        //Задержка для экспериментов с корутинами
        private const val DELAY_LOADING = 1500L

        private const val EMPTY_RESULT_MESSAGE = "Отсутсвуют данные. Измените/повторите запрос."
    }

    fun translateLiveData(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    fun networkStateLiveData(): LiveData<Boolean> {
        return liveDataForNetworkState
    }

    override fun getData(word: String, isOnline: Boolean) {
        liveDataForViewToObserve.value = AppState.Loading(null)
        cancelJob()

        viewModelCoroutineScope.launch {
            startInteractor(word, isOnline)
        }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) {
        delay(DELAY_LOADING)

        val result = interactor.getData(word, isOnline)

        if (result.dictionaryEntryList.isNotEmpty()) {
            liveDataForViewToObserve.postValue(AppState.Success(result))
        } else {
            liveDataForViewToObserve.postValue(AppState.Error(Exception(EMPTY_RESULT_MESSAGE)))
        }
    }

    override fun handleError(error: Throwable) {
        liveDataForViewToObserve.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        liveDataForViewToObserve.value = AppState.Success(null)
        super.onCleared()
    }

    override fun getNetworkState(): LiveData<Boolean> {
        compositeDisposable +=
            networkState
                .doOnNext { state ->
                    liveDataForNetworkState.postValue(state == NetworkState.CONNECTED)
                }
                .publish()
                .connect()

        return super.getNetworkState()
    }
}