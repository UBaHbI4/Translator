package softing.ubah4ukdev.translator.di

import androidx.savedstate.SavedStateRegistryOwner
import dagger.assisted.AssistedFactory

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
@AssistedFactory
interface MainViewModelAssistedFactory {
    fun create(owner: SavedStateRegistryOwner): MainViewModelFactory
}