package softing.ubah4ukdev.translator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import softing.ubah4ukdev.translator.domain.model.AppState

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
abstract class BaseViewModel<T : AppState>(
    protected val liveDataForViewToObserve: MutableLiveData<T> = MutableLiveData(),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : ViewModel() {

    open fun getData(word: String): LiveData<T> = liveDataForViewToObserve

    override fun onCleared() {
        compositeDisposable.clear()
    }
}

