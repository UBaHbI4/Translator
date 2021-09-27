package softing.ubah4ukdev.translator.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import softing.ubah4ukdev.translator.BuildConfig
import softing.ubah4ukdev.translator.domain.api.YandexApi
import softing.ubah4ukdev.translator.domain.api.YandexApiInterceptor
import javax.inject.Singleton

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.di.module
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *   Dagger модуль для ретрофита
 *
 *   2021.09.25
 *
 *   v1.0
 */
@Module
class YandexApiModule {

    private val gson: Gson =
        GsonBuilder()
            .create()

    @Singleton
    @Provides
    fun provideYandexApi(): YandexApi =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(YandexApiInterceptor)
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = if (BuildConfig.DEBUG) {
                            HttpLoggingInterceptor.Level.BODY
                        } else {
                            HttpLoggingInterceptor.Level.NONE
                        }
                    })
                    .build()
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(YandexApi::class.java)
}