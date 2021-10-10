package softing.ubah4ukdev.domain.repository.datasource

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.repository.domain.datasource
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *   Общий интерфейс для источника данных
 *
 *   2021.09.19
 *
 *   v1.0
 */
interface IDataSource<T> {

    /**
     * Получить перевод
     * @param word Слово, которое необходимо перевести
     * @return Observable<T>
     */
    suspend fun getData(word: String): T
}