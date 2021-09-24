package softing.ubah4ukdev.translator.domain.model

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.domain.model
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *
 *
 *   2021.09.19
 *
 *   v1.0
 */
sealed class AppState {

    data class Success(val data: DictionaryResult?) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}