package softing.ubah4ukdev.translator.di

import androidx.room.Room
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import softing.ubah4ukdev.domain.api.YandexApi
import softing.ubah4ukdev.domain.api.YandexApiInterceptor
import softing.ubah4ukdev.domain.repository.IRepository
import softing.ubah4ukdev.domain.repository.IRepositoryLocal
import softing.ubah4ukdev.domain.repository.RepositoryImpl
import softing.ubah4ukdev.domain.repository.RepositoryLocalImpl
import softing.ubah4ukdev.domain.repository.datasource.CacheDataSourceImpl
import softing.ubah4ukdev.domain.repository.datasource.NetworkDataSourceImpl
import softing.ubah4ukdev.domain.storage.WordStorage
import softing.ubah4ukdev.model.data.DictionaryResult
import softing.ubah4ukdev.screenfavourite.FavouriteFragment
import softing.ubah4ukdev.screenfavourite.FavouriteInteractor
import softing.ubah4ukdev.screenfavourite.FavouriteViewModel
import softing.ubah4ukdev.screenhistory.HistoryFragment
import softing.ubah4ukdev.screenhistory.HistoryInteractor
import softing.ubah4ukdev.screenhistory.HistoryViewModel
import softing.ubah4ukdev.translator.BuildConfig
import softing.ubah4ukdev.translator.view.main.MainFragment
import softing.ubah4ukdev.translator.view.main.MainInteractor
import softing.ubah4ukdev.translator.view.main.MainViewModel
import softing.ubah4ukdev.utils.Di.DiConst
import softing.ubah4ukdev.utils.network.NetworkStateObservable

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

    private const val PERSISTED = "Persisted"
    private const val IN_MEMORY = "InMemory"

    private const val DATABASE_NAME = "translator_database"

    fun viewModelModule() = module {
        scope<MainFragment> {
            viewModel(qualifier = named(DiConst.MAIN_VIEW_MODEL)) {
                MainViewModel(
                    interactor = get(),
                    networkState = get(),
                )
            }
        }

        scope<HistoryFragment> {
            viewModel(qualifier = named(DiConst.HISTORY_VIEW_MODEL)) {
                HistoryViewModel(interactor = get())
            }
        }

        scope<FavouriteFragment> {
            viewModel(qualifier = named(DiConst.FAVOURITE_VIEW_MODEL)) {
                FavouriteViewModel(interactor = get())
            }
        }
    }

    fun interactorModule() = module {
        factory {
            MainInteractor(
                repositoryLocal = get(),
                repositoryRemote = get()
            )
        }

        factory {
            HistoryInteractor(
                repositoryLocal = get(),
            )
        }

        factory {
            FavouriteInteractor(
                repositoryLocal = get(),
            )
        }
    }

    fun networkModule() = module {
        single { NetworkStateObservable(context = androidContext()) }
    }

    fun repositoryModule() = module {
        single<IRepository<DictionaryResult>> {
            RepositoryImpl(
                dataSource = NetworkDataSourceImpl(
                    yandexApi = get()
                )
            )
        }

        single<IRepositoryLocal> {
            RepositoryLocalImpl(
                dataSource = CacheDataSourceImpl(
                    get(
                        named(PERSISTED)
                    )
                )
            )
        }
    }

    fun yandexApiModule() = module {
        single<Interceptor> {
            YandexApiInterceptor()
        }

        single<Gson> {
            GsonBuilder()
                .create()
        }

        single<YandexApi> {
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(interceptor = get())
                        .addInterceptor(HttpLoggingInterceptor().apply {
                            level = if (BuildConfig.DEBUG) {
                                HttpLoggingInterceptor.Level.BODY
                            } else {
                                HttpLoggingInterceptor.Level.NONE
                            }
                        })
                        .build()
                )
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(get()))
                .build()
                .create(YandexApi::class.java)
        }
    }

    fun navigationModule() = module {
        single<Cicerone<Router>> {
            Cicerone.create()
        }

        single<NavigatorHolder> {
            get<Cicerone<Router>>().getNavigatorHolder()
        }

        single<Router> {
            get<Cicerone<Router>>().router
        }
    }

    fun storageModule() = module {
        single<WordStorage>(qualifier = named(PERSISTED)) {
            Room.databaseBuilder(androidContext(), WordStorage::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }

        single<WordStorage>(qualifier = named(IN_MEMORY)) {
            Room.inMemoryDatabaseBuilder(androidContext(), WordStorage::class.java)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}