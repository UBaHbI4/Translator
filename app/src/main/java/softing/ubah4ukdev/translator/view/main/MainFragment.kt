package softing.ubah4ukdev.translator.view.main

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import softing.ubah4ukdev.translator.R
import softing.ubah4ukdev.translator.databinding.FragmentMainBinding
import softing.ubah4ukdev.translator.domain.model.AppState
import softing.ubah4ukdev.translator.domain.model.DictionaryEntry
import softing.ubah4ukdev.translator.view.base.BaseFragment
import softing.ubah4ukdev.translator.view.main.adapter.WordAdapter

/**
 *   Project: Translator
 *
 *   Package: softing.ubah4ukdev.translator.view.main
 *
 *   Created by Ivan Sheynmaer
 *
 *   Description:
 *
 *   2021.10.06
 *
 *   v1.0
 */
class MainFragment : BaseFragment<AppState, MainInteractor>(), WordAdapter.Delegate {

    companion object {
        private const val INPUT_METHOD_MANAGER_FLAGS = 0

        fun newInstance(): Fragment = MainFragment()
    }

    private val binding: FragmentMainBinding by viewBinding()
    override val model: MainViewModel by viewModel()

    private val wordAdapter by lazy { WordAdapter(this) }

    private val textWatcher = object : TextWatcher {
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            if (binding.searchEditText.text != null && binding.searchEditText.text.toString()
                    .isNotEmpty()
            ) {
                binding.find.isEnabled = true
                binding.clearText.visibility = View.VISIBLE
            } else {
                binding.find.isEnabled = false
                binding.clearText.visibility = View.GONE
            }
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun afterTextChanged(s: Editable) {}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.networkStateLiveData().observe(viewLifecycleOwner, Observer<Boolean> {
            isNetworkAvailable = it
        })
        model.getNetworkState()

        model.translateLiveData().observe(viewLifecycleOwner, Observer<AppState> { renderData(it) })

        init()
    }

    private fun init() {
        with(binding) {
            searchEditText.addTextChangedListener(textWatcher)
            searchEditText.setOnEditorActionListener { view, actionId, event ->
                if (actionId == android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH) {
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
                    LinearLayoutManager(requireContext())
                adapter = wordAdapter
                itemAnimator = DefaultItemAnimator()
                addItemDecoration(
                    DividerItemDecoration(
                        requireContext(),
                        LinearLayoutManager.VERTICAL
                    )
                )
            }
        }
    }

    private fun hideKeyboardForTextView() {
        val view = requireActivity().currentFocus
        view?.let {
            val inputMethodManager = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as
                    InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, INPUT_METHOD_MANAGER_FLAGS)
        }
        (view as? TextView)?.clearFocus()
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
        Toast.makeText(
            requireContext(),
            word.translatesList.joinToString(separator = ",\n"),
            Toast.LENGTH_LONG
        )
            .show()
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
                        progressBarHorizontal.visibility = android.view.View.VISIBLE
                        progressBarRound.visibility = android.view.View.GONE
                        progressBarHorizontal.progress = appState.progress
                    } else {
                        progressBarHorizontal.visibility = android.view.View.GONE
                        progressBarRound.visibility = android.view.View.VISIBLE
                    }
                }
            }
            is AppState.Error -> {
                showErrorScreen(appState.error.message)
            }
        }
    }

}