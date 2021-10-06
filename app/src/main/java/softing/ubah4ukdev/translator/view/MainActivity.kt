package softing.ubah4ukdev.translator.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import org.koin.android.ext.android.inject
import softing.ubah4ukdev.translator.R
import softing.ubah4ukdev.translator.databinding.ActivityMainBinding
import softing.ubah4ukdev.translator.view.main.MainScreen

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val navigatorHolder: NavigatorHolder by inject()
    private val router: Router by inject()
    private val navigator = AppNavigator(this, R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        savedInstanceState ?: router.replaceScreen(MainScreen())
    }

    private fun init() {
        binding.navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_main -> {
                    router.replaceScreen(MainScreen())
                    true
                }
                else -> false
            }
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }
}