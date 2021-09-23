package softing.ubah4ukdev.translator.domain.repository.datasource

import softing.ubah4ukdev.translator.domain.api.YandexApiFactory

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.domain.repository.datasource
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *   Фабрика для создания инстанса удаленного источника данных
 *
 *   2021.09.20
 *
 *   v1.0
 */
object NetworkDataSourceFactory {
    fun create(): INetworkDataSource = NetworkDataSourceImpl(YandexApiFactory.create())
}