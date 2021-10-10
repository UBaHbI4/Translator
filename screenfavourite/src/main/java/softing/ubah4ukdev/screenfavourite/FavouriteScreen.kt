package softing.ubah4ukdev.screenfavourite

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.view.history
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *
 *
 *   2021.10.07
 *
 *   v1.0
 */
class FavouriteScreen : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment =
        FavouriteFragment.newInstance()
}