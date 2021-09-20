package softing.ubah4ukdev.translator.view.main

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import softing.ubah4ukdev.translator.R
import softing.ubah4ukdev.translator.databinding.ActivityMainBinding
import softing.ubah4ukdev.translator.domain.model.AppState
import softing.ubah4ukdev.translator.domain.model.DictionaryEntry
import softing.ubah4ukdev.translator.extensions.showSnakeBar
import softing.ubah4ukdev.translator.presenter.IPresenter
import softing.ubah4ukdev.translator.view.base.BaseActivity
import softing.ubah4ukdev.translator.view.base.IView
import softing.ubah4ukdev.translator.view.main.adapter.WordAdapter

class MainActivity : BaseActivity<AppState>(), WordAdapter.Delegate {

    private val binding: ActivityMainBinding by viewBinding()

    private val adapter by lazy { WordAdapter(this) }

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

    override fun createPresenter(): IPresenter<AppState, IView> {
        return MainPresenterImpl()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.searchEditText.addTextChangedListener(textWatcher)

        binding.clearText.setOnClickListener {
            binding.searchEditText.setText("")
            binding.find.isEnabled = false
            adapter.clear()
        }

        binding.find.setOnClickListener {
            presenter.getData(binding.searchEditText.text.toString())

            val view = this.currentFocus
            view?.let {
                val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as
                        InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
            }
            binding.searchEditText.clearFocus()
        }

        binding.mainActivityRecyclerview.layoutManager =
            LinearLayoutManager(applicationContext)
        binding.mainActivityRecyclerview.adapter = adapter
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                if (appState.data == null || appState.data.dictionaryEntryList.isEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    showViewSuccess()
                    adapter.setData(ArrayList(appState.data.dictionaryEntryList))
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    binding.progressBarHorizontal.visibility = VISIBLE
                    binding.progressBarRound.visibility = GONE
                    binding.progressBarHorizontal.progress = appState.progress
                } else {
                    binding.progressBarHorizontal.visibility = GONE
                    binding.progressBarRound.visibility = VISIBLE
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
            presenter.getData(binding.searchEditText.text.toString())
        }
    }

    private fun showViewSuccess() {
        binding.successFrame.isVisible = true
        binding.loadingFrame.isVisible = false
        binding.errorFrame.isVisible = false
    }

    private fun showViewLoading() {
        binding.successFrame.isVisible = false
        binding.loadingFrame.isVisible = true
        binding.errorFrame.isVisible = false
    }

    private fun showViewError() {
        binding.successFrame.isVisible = false
        binding.loadingFrame.isVisible = false
        binding.errorFrame.isVisible = true
    }

    override fun onItemPicked(word: DictionaryEntry) {
        Toast.makeText(this, word.translatesList.joinToString(separator = ",\n"), Toast.LENGTH_LONG).show()
    }
}