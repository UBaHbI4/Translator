package softing.ubah4ukdev.domain.repository

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.repository.domain.repository
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *   Интерфейс репозитория с описанием методов получения данных
 *
 *   2021.09.19
 *
 *   v1.0
 */
interface IRepository<T> {

    /**
     * Получить перевод
     * @param word Слово, которое необходимо перевести
     * @return Observable<T>
     */
    suspend fun getData(word: String): T

}