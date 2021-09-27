package softing.ubah4ukdev.translator.di

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import softing.ubah4ukdev.translator.domain.scheduler.Schedulers
import softing.ubah4ukdev.translator.utils.network.NetworkStateObservable
import softing.ubah4ukdev.translator.view.main.MainInteractor
import softing.ubah4ukdev.translator.view.main.MainViewModel

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.di
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *
 *
 *   2021.09.25
 *
 *   v1.0
 */
class MainViewModelFactory @AssistedInject constructor(
    val interactor: MainInteractor,
    val schedulers: Schedulers,
    val networkState: NetworkStateObservable,
    @Assisted owner: SavedStateRegistryOwner
) : AbstractSavedStateViewModelFactory(owner, null) {
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T = MainViewModel(interactor, schedulers, networkState, handle) as T
}