package com.pandiandcode.cardsagainsthumanity.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.pandiandcode.cardsagainsthumanity.R
import com.pandiandcode.cardsagainsthumanity.databinding.FragmentBlackCardBinding
import com.pandiandcode.cardsagainsthumanity.viewModel.BlackCardViewModel
import com.pandiandcode.cardsagainsthumanity.viewModel.ErrorViewModel
import org.koin.android.ext.android.inject

class BlackCardFragment : Fragment() {
    private val blackCardViewModel: BlackCardViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        FragmentBlackCardBinding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = this@BlackCardFragment
                viewModel = blackCardViewModel.also {
                    it.error.observe(viewLifecycleOwner, errorObserver)
                    it.visible.observe(viewLifecycleOwner, visibleObserver)
                }
            }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        blackCardViewModel.load()
    }

    private val errorObserver = Observer<ErrorViewModel> {
        if (it.show) {
            AlertDialog.Builder(context)
                .setTitle("Error")
                .setMessage(it.message)
                .setNeutralButton(
                    getString(R.string.ok)
                ) { _, _ ->
                    it.close()
                }.create().show()
        }
    }

    private val visibleObserver = Observer<Boolean> { visible ->
        if (visible.not()) {
            activity?.finish()
        }
    }
}