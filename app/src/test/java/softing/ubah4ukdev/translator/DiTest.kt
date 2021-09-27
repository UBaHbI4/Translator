package softing.ubah4ukdev.translator

import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import softing.ubah4ukdev.translator.di.Di

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *
 *
 *   2021.09.27
 *
 *   v1.0
 */
class DiTest : KoinTest {

    @Test
    fun testFunc() {

        val listModules = listOf(
            Di.viewModelModule(),
            Di.interactorModule(),
            Di.networkModule(),
            Di.repositoryModule(),
            Di.yandexApiModule()
        )

        checkModules { listModules }
    }
}