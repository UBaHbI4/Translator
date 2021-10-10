package softing.ubah4ukdev.utils.extensions

import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.extensions
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *
 *
 *   2021.09.20
 *
 *   v1.0
 */

/**
 * Назначить вьюхе обработчик клика
 */
fun View.click(click: () -> Unit) = setOnClickListener { click() }

/**
 * Показать SnakeBar
 * @param text Текст, который необходимо показать в SnackBar
 * @param length Продолжительность показа, по умолчанию LENGTH_SHORT
 */
fun View.showSnakeBar(text: String, length: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, text, length).show()
}

fun Fragment.arguments(vararg arguments: Pair<String, Any>): Fragment {
    this.arguments = bundleOf(*arguments)
    return this
}