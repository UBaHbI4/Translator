package softing.ubah4ukdev.translator.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import softing.ubah4ukdev.translator.BuildConfig
import softing.ubah4ukdev.translator.domain.api.YandexApi
import softing.ubah4ukdev.translator.domain.api.YandexApiInterceptor
import softing.ubah4ukdev.translator.domain.model.DictionaryResult
import softing.ubah4ukdev.translator.domain.repository.IRepository
import softing.ubah4ukdev.translator.domain.repository.RepositoryImpl
import softing.ubah4ukdev.translator.domain.repository.datasource.CacheDataSourceImpl
import softing.ubah4ukdev.translator.domain.repository.datasource.NetworkDataSourceImpl
import softing.ubah4ukdev.translator.domain.scheduler.DefaultSchedulers
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
 *   2021.09.26
 *
 *   v1.0
 */
object Di {
    private const val LOCAL = "LOCAL"
    private const val REMOTE = "REMOTE"

    fun viewModelModule() = module {
        single<Schedulers> { DefaultSchedulers() }

        viewModel {
            MainViewModel(
                interactor = get(),
                schedulers = get(),
                networkState = get(),
                get()
            )
        }
    }

    fun interactorModule() = module {
        factory {
            MainInteractor(
                repositoryLocal = get(named(LOCAL)),
                repositoryRemote = get(named(REMOTE))
            )
        }
    }

    fun networkModule() = module {
        single { NetworkStateObservable(context = androidContext()) }
    }

    fun repositoryModule() = module {
        single<IRepository<DictionaryResult>>(qualifier = named(REMOTE)) {
            RepositoryImpl(
                dataSource = NetworkDataSourceImpl(
                    yandexApi = get()
                )
            )
        }

        single<IRepository<DictionaryResult>>(qualifier = named(LOCAL)) {
            RepositoryImpl(
                dataSource = CacheDataSourceImpl()
            )
        }
    }

    fun yandexApiModule() = module {
        single<Gson> {
            GsonBuilder()
                .create()
        }

        single<YandexApi> {
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
                .addConverterFactory(GsonConverterFactory.create(get()))
                .build()
                .create(YandexApi::class.java)
        }
    }
}