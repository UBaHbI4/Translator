package softing.ubah4ukdev.translator.view.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.view.main
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *
 *
 *   2021.10.06
 *
 *   v1.0
 */
class MainScreen : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment =
        MainFragment.newInstance()
}