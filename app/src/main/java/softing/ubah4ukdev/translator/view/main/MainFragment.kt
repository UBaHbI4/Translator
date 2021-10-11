package softing.ubah4ukdev.translator.view.main

import android.app.AlertDialog
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.terrakok.cicerone.Router
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named
import softing.ubah4ukdev.domain.storage.entity.WordTranslate
import softing.ubah4ukdev.model.data.AppState
import softing.ubah4ukdev.model.data.DictionaryResult
import softing.ubah4ukdev.screendetail.DetailScreen
import softing.ubah4ukdev.translator.R
import softing.ubah4ukdev.translator.databinding.FragmentMainBinding
import softing.ubah4ukdev.translator.view.main.adapter.WordAdapter
import softing.ubah4ukdev.utils.Di.DiConst
import softing.ubah4ukdev.utils.extensions.showSnakeBar
import softing.ubah4ukdev.utils.mapToListWordTranslate
import softing.ubah4ukdev.utils.viewById

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
class MainFragment : Fragment(R.layout.fragment_main), WordAdapter.Delegate {

    private val scope = getKoin().createScope<MainFragment>()

    private var isNetworkAvailable: Boolean = false
    private lateinit var binding: FragmentMainBinding
    private val model: MainViewModel = scope.get(qualifier = named(name = DiConst.MAIN_VIEW_MODEL))
    private val router: Router by inject()
    private val wordAdapter by lazy { WordAdapter(this) }

    private val mainRV by viewById<RecyclerView>(R.id.main_rv)

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

    private fun noInternetMessageShow() {
        showMessage {
            Toast.makeText(
                requireContext(),
                getString(R.string.no_internet_message),
                Toast.LENGTH_LONG
            )
                .show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        /**
         * Без этого кода и без model.reset() у меня начинаются задвоения и затроения
         * т.е. если сделать поиск в истории и нажать назад, то первый раз переходим.
         * После второго поиска, нужно нажать 2 раза, потом 3 и т.д. чтобы вернуться на стартовый
         * экран. Как я понял у меня не вызывается метод у вьюмодели onCleared. Проблема вроде
         * решена, теперь все стабильно. Но надо разобраться в чем дело или лучше использовать
         * гугловскую навигацию.
         */
        model.networkStateLiveData().removeObservers(requireActivity())
        model.translateLiveData().removeObservers(requireActivity())
        model.findHistoryLiveData().removeObservers(requireActivity())
        model.getNetworkState().removeObservers(requireActivity())

        init()
    }

    override fun onPause() {
        super.onPause()
        model.reset()
    }

    private fun init() {
        with(binding) {
            searchEditText.addTextChangedListener(textWatcher)
            searchEditText.setOnEditorActionListener { view, actionId, _ ->
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


        }

        with(mainRV) {
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

        model.networkStateLiveData().observe(viewLifecycleOwner, Observer<Boolean> {
            isNetworkAvailable = it
            binding.root.showSnakeBar(String.format(getString(R.string.internet_active), it))
        })
        model.getNetworkState()
        model.translateLiveData().observe(viewLifecycleOwner, Observer<AppState> {
            when (it) {
                is AppState.Success -> {
                    if (it.data == null || (it.data as DictionaryResult).dictionaryEntryList.isEmpty()) {
                        showErrorScreen(getString(R.string.empty_server_response_on_success))
                    } else {
                        showViewSuccess()
                        wordAdapter.setData(ArrayList(mapToListWordTranslate(it.data as DictionaryResult)))
                        model.saveToHistory(it.data as DictionaryResult)
                    }
                }
                is AppState.Loading -> {
                    showViewLoading()
                    with(binding) {
                        if (it.progress != null) {
                            progressBarHorizontal.isVisible = true
                            progressBarRound.isVisible = false
                            it.progress?.let { progress ->
                                progressBarHorizontal.progress = progress
                            }
                        } else {
                            progressBarHorizontal.isVisible = false
                            progressBarRound.isVisible = true
                        }
                    }
                }
                is AppState.Error -> {
                    showErrorScreen(it.error.message)
                }
            }
        })

        model.findHistoryLiveData().observe(viewLifecycleOwner, Observer<AppState>
        {
            when (it) {
                is AppState.Success -> {
                    if (it?.data != null) {
                        router.navigateTo(
                            DetailScreen(word = (it.data as WordTranslate))
                        )
                    }
                }
                is AppState.Loading -> {
                    showViewLoading()
                    with(binding) {
                        if (it.progress != null) {
                            progressBarHorizontal.isVisible = true
                            progressBarRound.isVisible = false
                            it.progress?.let { progress ->
                                progressBarHorizontal.progress = progress
                            }
                        } else {
                            progressBarHorizontal.isVisible = false
                            progressBarRound.isVisible = true
                        }
                    }
                }
                is AppState.Error -> {
                    binding.loadingFrame.isVisible = false
                    showMessage {
                        Toast.makeText(
                            requireContext(),
                            it.error.message.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })

        model.favouriteLiveData().observe(viewLifecycleOwner, Observer<AppState> {
            when (it) {
                is AppState.Success -> {
                    if ((it?.data as Long) > 0) {
                        showViewSuccess()
                        showMessage {
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.success_added_to_favourite),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
                is AppState.Loading -> {
                    showViewLoading()
                    with(binding) {
                        if (it.progress != null) {
                            progressBarHorizontal.isVisible = true
                            progressBarRound.isVisible = false
                            it.progress?.let { progress ->
                                progressBarHorizontal.progress = progress
                            }
                        } else {
                            progressBarHorizontal.isVisible = false
                            progressBarRound.isVisible = true
                        }
                    }
                }
                is AppState.Error -> {
                    binding.loadingFrame.isVisible = false
                    showMessage {
                        Toast.makeText(
                            requireContext(),
                            it.error.message.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    private fun search() {
        val inflater: LayoutInflater = requireActivity().layoutInflater
        val mView: View = inflater.inflate(R.layout.dialog_find_in_history, null)
        val text: TextView = mView.findViewById(R.id.target_word)

        val dialogClickListener =
            DialogInterface.OnClickListener { dialog, which ->
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> {
                        text?.text.let {
                            model.findInHistory(it.toString())
                        }
                    }
                    DialogInterface.BUTTON_NEGATIVE -> {
                    }
                }
            }

        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder
            .setTitle(getString(R.string.title_alert_find))
            .setView(mView)
            .setPositiveButton(getString(R.string.alert_positive_text), dialogClickListener)
            .setNegativeButton(getString(R.string.alert_negative_text), dialogClickListener)
            .show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.search -> {
                search()
                true
            }
            else -> false
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

    override fun onItemPicked(word: WordTranslate) {
        router.navigateTo(DetailScreen(word = word))
    }

    override fun onFavouritePicked(word: WordTranslate) {
        model.saveToFavourite(word)
    }

    private fun showMessage(toast: () -> Unit) {
        toast()
    }

    companion object {
        private const val INPUT_METHOD_MANAGER_FLAGS = 0

        fun newInstance(): Fragment = MainFragment()
    }
}