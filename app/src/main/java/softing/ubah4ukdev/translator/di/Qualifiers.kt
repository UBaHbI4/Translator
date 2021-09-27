package softing.ubah4ukdev.translator.di

import javax.inject.Qualifier

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.di
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *   Qualifiers
 *
 *   2021.09.25
 *
 *   v1.0
 */
class Qualifiers {

    @Qualifier
    annotation class Local

    @Qualifier
    annotation class Remote
}