package softing.ubah4ukdev.translator.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.*
import softing.ubah4ukdev.domain.storage.entity.WordTranslate
import softing.ubah4ukdev.model.data.AppState

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.viewmodel
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
abstract class BaseMainViewModel<T : AppState>(
    protected val translateLiveData: MutableLiveData<T> = MutableLiveData(),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    protected val networkStateLiveData: MutableLiveData<Boolean> = MutableLiveData(),
    protected val historyLiveData: MutableLiveData<T> = MutableLiveData(),
    protected val favouritesLiveData: MutableLiveData<T> = MutableLiveData()
) : ViewModel() {

    companion object {
        private const val CANCEL_MESSAGE = "Уже не актуально."
    }

    protected val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    abstract fun getData(word: String, isOnline: Boolean)

    abstract fun saveToFavourite(word: WordTranslate)

    abstract fun findInHistory(word: String)

    open fun getNetworkState(): LiveData<Boolean> = networkStateLiveData

    override fun onCleared() {
        super.onCleared()
        cancelJob()
        compositeDisposable.clear()
    }

    protected fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren(CancellationException(CANCEL_MESSAGE))
    }

    abstract fun handleError(error: Throwable)
}