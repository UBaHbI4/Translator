package softing.ubah4ukdev.translator.view.base

import softing.ubah4ukdev.translator.domain.model.AppState

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.view
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *   Интерфейс для вьюхи
 *
 *   2021.09.19
 *
 *   v1.0
 */
interface IView {
    /**
     * Отобразить результаты
     * @param appState State
     */
    fun renderData(appState: AppState)
}