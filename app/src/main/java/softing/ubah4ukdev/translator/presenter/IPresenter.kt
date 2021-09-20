package softing.ubah4ukdev.translator.presenter

import softing.ubah4ukdev.translator.domain.model.AppState
import softing.ubah4ukdev.translator.view.base.IView

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.presenter
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *   Интерфейс презентера
 *
 *   2021.09.19
 *
 *   v1.0
 */
interface IPresenter<T : AppState, V : IView> {
    /**
     * Привязать вьюху
     * @view Вьюха
     */
    fun attachView(view: V)

    /**
     * Отвязать вьюху
     * @view Вьюха
     */
    fun detachView(view: V)

    /**
     * Получить перевод
     * @param word Слово, которое необходимо перевести
     */
    fun getData(word: String)
}