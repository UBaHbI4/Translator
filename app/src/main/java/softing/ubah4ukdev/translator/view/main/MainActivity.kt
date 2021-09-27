package softing.ubah4ukdev.translator.view.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import softing.ubah4ukdev.translator.R
import softing.ubah4ukdev.translator.databinding.ActivityMainBinding
import softing.ubah4ukdev.translator.domain.model.AppState
import softing.ubah4ukdev.translator.domain.model.DictionaryEntry
import softing.ubah4ukdev.translator.view.base.BaseActivity
import softing.ubah4ukdev.translator.view.main.adapter.WordAdapter

class MainActivity : BaseActivity<AppState, MainInteractor>(), WordAdapter.Delegate {

    companion object {
        private const val INPUT_METHOD_MANAGER_FLAGS = 0
    }

    override val model: MainViewModel by stateViewModel()

    private val binding: ActivityMainBinding by viewBinding()

    private val wordAdapter by lazy { WordAdapter(this) }

    private val textWatcher = object : TextWatcher {
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            if (binding.searchEditText.text != null && binding.searchEditText.text.toString()
                    .isNotEmpty()
            ) {
                binding.find.isEnabled = true
                binding.clearText.visibility = VISIBLE
            } else {
                binding.find.isEnabled = false
                binding.clearText.visibility = GONE
            }
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun afterTextChanged(s: Editable) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        model.networkStateLiveData().observe(this@MainActivity, Observer<Boolean> {
            isNetworkAvailable = it
        })
        model.getNetworkState()

        model.translateLiveData().observe(this@MainActivity, Observer<AppState> { renderData(it) })

        init()
    }

    private fun init() {
        with(binding) {
            searchEditText.addTextChangedListener(textWatcher)
            searchEditText.setOnEditorActionListener { view, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (view.text.isNotEmpty()) {
                        if (isNetworkAvailable) {
                            model.getData(view.text.toString(), isNetworkAvailable)
                            hideKeyboardForTextView()
                            true
                        } else {
                            wordAdapter.clear()
                            hideKeyboardForTextView()
                            noInternetMessageShow()
                            false
                        }
                    } else {
                        false
                    }
                } else {
                    false
                }
            }

            clearText.setOnClickListener {
                binding.searchEditText.setText("")
                wordAdapter.clear()
            }

            find.isEnabled = false
            find.setOnClickListener {
                if (isNetworkAvailable) {
                    model.getData(binding.searchEditText.text.toString(), isNetworkAvailable)
                    hideKeyboardForTextView()
                } else {
                    hideKeyboardForTextView()
                    wordAdapter.clear()
                    noInternetMessageShow()
                }
            }

            with(mainActivityRecyclerview) {
                layoutManager =
                    LinearLayoutManager(applicationContext)
                adapter = wordAdapter
                itemAnimator = DefaultItemAnimator()
                addItemDecoration(
                    DividerItemDecoration(
                        baseContext,
                        LinearLayoutManager.VERTICAL
                    )
                )
            }
        }
    }

    private fun hideKeyboardForTextView() {
        val view = this.currentFocus
        view?.let {
            val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as
                    InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, INPUT_METHOD_MANAGER_FLAGS)
        }
        (view as? TextView)?.clearFocus()
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                if (appState.data == null || appState.data.dictionaryEntryList.isEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    showViewSuccess()
                    wordAdapter.setData(ArrayList(appState.data.dictionaryEntryList))
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                with(binding) {
                    if (appState.progress != null) {
                        progressBarHorizontal.visibility = VISIBLE
                        progressBarRound.visibility = GONE
                        progressBarHorizontal.progress = appState.progress
                    } else {
                        progressBarHorizontal.visibility = GONE
                        progressBarRound.visibility = VISIBLE
                    }
                }
            }
            is AppState.Error -> {
                showErrorScreen(appState.error.message)
            }
        }
    }

    private fun showErrorScreen(error: String?) {
        showViewError()
        binding.errorTextview.text = error ?: getString(R.string.undefined_error)
        binding.reloadButton.setOnClickListener {
            if (isNetworkAvailable) {
                model.getData(binding.searchEditText.text.toString(), isNetworkAvailable)
                hideKeyboardForTextView()
            } else {
                hideKeyboardForTextView()
                wordAdapter.clear()
                noInternetMessageShow()
            }
        }
    }

    private fun showViewSuccess() {
        with(binding) {
            successFrame.isVisible = true
            loadingFrame.isVisible = false
            errorFrame.isVisible = false
        }
    }

    private fun showViewLoading() {
        with(binding) {
            successFrame.isVisible = false
            loadingFrame.isVisible = true
            errorFrame.isVisible = false
        }
    }

    private fun showViewError() {
        with(binding) {
            successFrame.isVisible = false
            loadingFrame.isVisible = false
            errorFrame.isVisible = true
        }
    }

    override fun onItemPicked(word: DictionaryEntry) {
        Toast.makeText(this, word.translatesList.joinToString(separator = ",\n"), Toast.LENGTH_LONG)
            .show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        model.saveLastWord(binding.searchEditText.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        model.getLastWord().let {
            /**
             * Состояние и так восстанавливается. Но можно перепослать запрос, чтобы
             * получить актуальные данные, если расскоментировать код ниже:
             * //model.getData(it)
             */
        }
    }
}