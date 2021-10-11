package softing.ubah4ukdev.screenfavourite

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.terrakok.cicerone.Router
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named
import softing.ubah4ukdev.domain.storage.entity.WordFavourite
import softing.ubah4ukdev.model.data.AppState
import softing.ubah4ukdev.screendetail.DetailScreen
import softing.ubah4ukdev.screenfavourite.adapter.FavouriteWordAdapter
import softing.ubah4ukdev.screenfavourite.databinding.FragmentFavouriteBinding
import softing.ubah4ukdev.utils.Di.DiConst
import softing.ubah4ukdev.utils.mapFavouriteToTranslate

class FavouriteFragment : Fragment(R.layout.fragment_favourite), FavouriteWordAdapter.Delegate {

    private val scope = getKoin().createScope<FavouriteFragment>()

    private val model: FavouriteViewModel =
        scope.get(qualifier = named(name = DiConst.FAVOURITE_VIEW_MODEL))
    private lateinit var binding: FragmentFavouriteBinding
    private val favouriteWordAdapter by lazy { FavouriteWordAdapter(this) }
    private val router: Router by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun clearFavourite() {
        model.clearFavourite()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.favourite_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.clear -> {
                clearFavourite()
                true
            }
            else -> false
        }

    private fun init() {
        with(binding) {

            model.getData()

            with(favouriteRv) {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = favouriteWordAdapter
                itemAnimator = DefaultItemAnimator()
                addItemDecoration(
                    DividerItemDecoration(
                        requireContext(),
                        LinearLayoutManager.VERTICAL
                    )
                )
            }
        }

        model.translateLiveData().observe(viewLifecycleOwner, Observer<AppState> {
            when (it) {
                is AppState.Success -> {
                    if (it.data == null || (it.data as List<WordFavourite>).isEmpty()) {
                        showErrorScreen(getString(R.string.empty_server_response_on_success))
                    } else {
                        showViewSuccess()
                        favouriteWordAdapter.setData(ArrayList(it.data as List<WordFavourite>))
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
        model.clearLiveData().observe(viewLifecycleOwner, Observer<AppState> {
            when (it) {
                is AppState.Success -> {
                    if ((it?.data as Int) > 0) {
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.success_clear),
                            Toast.LENGTH_SHORT
                        ).show()
                        model.getData()
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
                    Toast.makeText(requireContext(), it.error.message.toString(), Toast.LENGTH_LONG)
                        .show()
                }
            }
        })
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

    private fun showErrorScreen(error: String?) {
        showViewError()
        binding.errorTextview.text = error ?: getString(R.string.undefined_error)
        binding.reloadButton.setOnClickListener {
            model.getData()
        }
    }

    override fun onItemPicked(word: WordFavourite) {
        router.navigateTo(
            DetailScreen(
                word = mapFavouriteToTranslate(
                    word
                )
            )
        )
    }

    companion object {
        fun newInstance() = FavouriteFragment()
    }
}