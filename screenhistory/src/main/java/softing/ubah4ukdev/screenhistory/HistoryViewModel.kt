package softing.ubah4ukdev.screenhistory

import androidx.lifecycle.LiveData
import kotlinx.coroutines.launch
import softing.ubah4ukdev.model.data.AppState

class HistoryViewModel(
    private val interactor: HistoryInteractor
) : BaseHistoryViewModel<AppState>() {

    companion object {
        private const val EMPTY_RESULT_MESSAGE = "Отсутсвуют данные. Измените/повторите запрос."
    }

    fun translateLiveData(): LiveData<AppState> {
        return historyWordsLiveData
    }

    fun clearLiveData(): LiveData<AppState> {
        return clearLiveData
    }

    override fun getData() {
        historyWordsLiveData.value = AppState.Loading(null)
        cancelJob()

        viewModelCoroutineScope.launch {
            val result = interactor.getData()
            if (result.isNotEmpty()) {
                historyWordsLiveData.postValue(AppState.Success(result))
            } else {
                historyWordsLiveData.postValue(
                    AppState.Error(
                        Exception(
                            EMPTY_RESULT_MESSAGE
                        )
                    )
                )
            }
        }
    }

    override fun clearHistory() {
        clearLiveData.value = AppState.Loading(null)
        cancelJob()

        viewModelCoroutineScope.launch {
            val result = interactor.repositoryLocal.clearHistory()
            if (result > 0) {
                clearLiveData.postValue(AppState.Success(result))
            } else {
                clearLiveData.postValue(
                    AppState.Error(
                        Exception(
                            EMPTY_RESULT_MESSAGE
                        )
                    )
                )
            }
        }
    }

    override fun onCleared() {
        historyWordsLiveData.value = AppState.Success(null)
        super.onCleared()
    }

    override fun handleError(error: Throwable) {
        historyWordsLiveData.postValue(AppState.Error(error))
    }
}