package softing.ubah4ukdev.translator.view.main

import androidx.lifecycle.LiveData
import io.reactivex.rxkotlin.plusAssign
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import softing.ubah4ukdev.domain.storage.entity.WordTranslate
import softing.ubah4ukdev.model.data.AppState
import softing.ubah4ukdev.model.data.DictionaryResult
import softing.ubah4ukdev.utils.mapToListWordTranslate
import softing.ubah4ukdev.utils.mapTranslateToFavourite
import softing.ubah4ukdev.utils.network.NetworkState
import softing.ubah4ukdev.utils.network.NetworkStateObservable

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
    private val interactor: MainInteractor,
    private val networkState: NetworkStateObservable,
) : BaseMainViewModel<AppState>() {

    companion object {
        //Задержка для экспериментов с корутинами
        private const val DELAY_LOADING = 300L

        private const val EMPTY_RESULT_MESSAGE = "Отсутсвуют данные. Измените/повторите запрос."
    }

    fun translateLiveData(): LiveData<AppState> {
        return translateLiveData
    }

    fun networkStateLiveData(): LiveData<Boolean> {
        return networkStateLiveData
    }

    fun findHistoryLiveData(): LiveData<AppState> {
        return historyLiveData
    }

    fun favouriteLiveData(): LiveData<AppState> {
        return favouritesLiveData
    }

    override fun findInHistory(word: String) {
        historyLiveData.postValue(AppState.Loading(null))
        cancelJob()

        viewModelCoroutineScope.launch {
            val result = interactor.repositoryLocal.findInHistoryByWord(word)

            if (result?.word != null) {
                historyLiveData.postValue(AppState.Success(result))
            } else {
                historyLiveData.postValue(AppState.Error(Exception(EMPTY_RESULT_MESSAGE)))
            }
        }
    }

    override fun saveToFavourite(word: WordTranslate) {
        favouritesLiveData.postValue(AppState.Loading(null))
        cancelJob()

        viewModelCoroutineScope.launch {
            val result =
                interactor.repositoryLocal.insertWordToFavourite(mapTranslateToFavourite(word))
            if (result > 0) {
                favouritesLiveData.postValue(AppState.Success(result))
            } else {
                favouritesLiveData.postValue(AppState.Error(Exception(EMPTY_RESULT_MESSAGE)))
            }
        }
    }

    override fun getData(word: String, isOnline: Boolean) {
        translateLiveData.postValue(AppState.Loading(null))
        cancelJob()

        viewModelCoroutineScope.launch {
            delay(DELAY_LOADING)
            val result = interactor.getData(word, isOnline)
            if (result.dictionaryEntryList.isNotEmpty()) {
                translateLiveData.postValue(AppState.Success(result))
            } else {
                translateLiveData.postValue(AppState.Error(Exception(EMPTY_RESULT_MESSAGE)))
            }
        }
    }

    override fun handleError(error: Throwable) {
        translateLiveData.postValue(AppState.Error(error))
        historyLiveData.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        historyLiveData.postValue(AppState.Success(null))
        super.onCleared()
    }

    override fun getNetworkState(): LiveData<Boolean> {
        compositeDisposable +=
            networkState
                .doOnNext { state ->
                    networkStateLiveData.postValue(state == NetworkState.CONNECTED)
                }
                .publish()
                .connect()
        return super.getNetworkState()
    }

    fun saveToHistory(translates: DictionaryResult) {
        viewModelCoroutineScope.launch {
            interactor
                .repositoryLocal
                .saveToDb(mapToListWordTranslate(translates))
        }
    }

    fun reset() {
        cancelJob()
        compositeDisposable.clear()
        historyLiveData.postValue(AppState.Success(null))
    }
}