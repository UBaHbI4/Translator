package softing.ubah4ukdev.screendetail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import softing.ubah4ukdev.domain.storage.entity.WordTranslate

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.view.detail
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
class DetailScreen(private val word: WordTranslate) : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment =
        DetailFragment.newInstance(word)
}