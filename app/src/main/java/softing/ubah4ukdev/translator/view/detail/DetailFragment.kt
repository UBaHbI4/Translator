package softing.ubah4ukdev.translator.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import softing.ubah4ukdev.translator.R
import softing.ubah4ukdev.translator.databinding.FragmentDetailBinding
import softing.ubah4ukdev.translator.domain.storage.entity.WordTranslate
import softing.ubah4ukdev.translator.extensions.arguments


class DetailFragment : Fragment(R.layout.fragment_detail) {

    companion object {
        private const val ARG_WORD = "argWord"

        fun newInstance(itemWord: WordTranslate) = DetailFragment()
            .arguments(ARG_WORD to itemWord)
    }

    private val itemWord: WordTranslate? by lazy { arguments?.getParcelable(ARG_WORD) }
    private lateinit var binding: FragmentDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        with(binding) {
            word.text = itemWord?.word
            mean.text = String.format(getString(R.string.word_mean), itemWord?.mean)
            translate.text = String.format(getString(R.string.word_translate), itemWord?.translate)
            pos.text = String.format(getString(R.string.word_pos), itemWord?.partOfSpeech)
            synonym.text = String.format(getString(R.string.word_synonym), itemWord?.synonym)
            example.text = String.format(getString(R.string.word_example), itemWord?.example)
            transcription.text =
                String.format(getString(R.string.word_transcription), itemWord?.transcription)

            Glide.with(imagePos)
                .load(itemWord?.imageURL)
                .error(R.drawable.ic_no_image)
                .placeholder(R.drawable.ic_place_holder_image)
                .into(imagePos)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
}