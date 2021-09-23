package softing.ubah4ukdev.translator.domain.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import softing.ubah4ukdev.translator.BuildConfig
import softing.ubah4ukdev.translator.domain.model.DictionaryResult

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.domain.datasource
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *   Интерфейс для Retrofit с описанием методов получения данных с API Yandex
 *
 *   2021.09.19
 *
 *   v1.0
 */
interface YandexApi {
    /**
     * Получить перевод
     * @param targetWord Слово, которое необходимо перевести
     * @return DictionaryResult
     */
    @GET("dicservice.json/lookup?key=${BuildConfig.API_TOKEN}&lang=en-ru")
    fun search(@Query("text") targetWord: String): Observable<DictionaryResult>
}