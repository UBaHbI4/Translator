package softing.ubah4ukdev.translator.view.favourite

import androidx.lifecycle.LiveData
import kotlinx.coroutines.launch
import softing.ubah4ukdev.translator.domain.model.AppState

class FavouriteViewModel(
    private val interactor: FavouriteInteractor
) : BaseFavouriteViewModel<AppState>() {

    companion object {
        private const val EMPTY_RESULT_MESSAGE = "Отсутсвуют данные. Измените/повторите запрос."
    }

    fun translateLiveData(): LiveData<AppState> {
        return favouriteWordsLiveData
    }

    fun clearLiveData(): LiveData<AppState> {
        return clearLiveData
    }

    override fun getData() {
        favouriteWordsLiveData.value = AppState.Loading(null)
        cancelJob()

        viewModelCoroutineScope.launch {
            val result = interactor.getData()
            if (result.isNotEmpty()) {
                favouriteWordsLiveData.postValue(AppState.Success(result))
            } else {
                favouriteWordsLiveData.postValue(AppState.Error(Exception(EMPTY_RESULT_MESSAGE)))
            }
        }
    }

    override fun clearFavourite() {
        clearLiveData.value = AppState.Loading(null)
        cancelJob()

        viewModelCoroutineScope.launch {
            val result = interactor.repositoryLocal.clearFavourite()
            if (result > 0) {
                clearLiveData.postValue(AppState.Success(result))
            } else {
                clearLiveData.postValue(AppState.Error(Exception(EMPTY_RESULT_MESSAGE)))
            }
        }
    }

    override fun onCleared() {
        favouriteWordsLiveData.value = AppState.Success(null)
        clearLiveData.value = AppState.Success(null)
        super.onCleared()
    }

    override fun handleError(error: Throwable) {
        favouriteWordsLiveData.postValue(AppState.Error(error))
    }
}