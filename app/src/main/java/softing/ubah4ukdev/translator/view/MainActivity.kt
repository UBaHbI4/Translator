package softing.ubah4ukdev.translator.view

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.BounceInterpolator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import org.koin.android.ext.android.inject
import softing.ubah4ukdev.screenfavourite.FavouriteScreen
import softing.ubah4ukdev.screenhistory.HistoryScreen
import softing.ubah4ukdev.translator.R
import softing.ubah4ukdev.translator.databinding.ActivityMainBinding
import softing.ubah4ukdev.translator.view.main.MainScreen
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val navigatorHolder: NavigatorHolder by inject()
    private val router: Router by inject()
    private val navigator = AppNavigator(this, R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT)
        super.onCreate(savedInstanceState)

        startAnimationAfterSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        showBottomNavigation()

        savedInstanceState ?: router.replaceScreen(MainScreen())
    }

    private fun showBottomNavigation() {
        Handler(Looper.getMainLooper()).postDelayed({
            ObjectAnimator.ofFloat(
                binding.navView,
                View.ALPHA,
                ALPHA_START_VALUE,
                ALPHA_END_VALUE
            )
                .apply {
                    duration = SHOW_NAVIGATION_DURATION
                }.also { it.start() }
        }, SHOW_NAVIGATION_DELAY)
    }

    private fun startAnimationAfterSplashScreen() {
        val splashScreen = installSplashScreen()
        var condition = true
        splashScreen.setKeepVisibleCondition { condition }

        Executors.newSingleThreadExecutor().execute {
            Thread.sleep(SPLASH_SCREEN_DURATION)
            /**
             * Тут можно что-то загружать, настройки например
             */
            condition = false
            splashScreen.setKeepVisibleCondition { condition }
        }

        splashScreen.setOnExitAnimationListener { splashScreenProvider ->
            ObjectAnimator.ofFloat(
                splashScreenProvider.view,
                View.TRANSLATION_Y,
                TRANSITION_START_VALUE,
                splashScreenProvider.view.height.toFloat()
            )
                .apply {
                    duration = ANIMATION_DURATION
                    doOnEnd { splashScreenProvider.remove() }
                    interpolator = BounceInterpolator()
                }.also { it.start() }
        }
    }

    private fun init() {
        binding.navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_main -> {
                    router.navigateTo(MainScreen())
                    true
                }
                R.id.navigation_history -> {
                    router.navigateTo(HistoryScreen())
                    true
                }
                R.id.navigation_favourite -> {
                    router.navigateTo(FavouriteScreen())
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

    companion object {
        private const val SPLASH_SCREEN_DURATION = 1000L
        private const val ANIMATION_DURATION = 1200L
        private const val TRANSITION_START_VALUE = 0f
        private const val ALPHA_START_VALUE = 0f
        private const val ALPHA_END_VALUE = 1f
        private const val SHOW_NAVIGATION_DURATION = 800L
        private const val SHOW_NAVIGATION_DELAY = 2600L
    }
}