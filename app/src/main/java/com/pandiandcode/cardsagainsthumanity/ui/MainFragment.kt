package com.pandiandcode.cardsagainsthumanity.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.pandiandcode.cardsagainsthumanity.R
import com.pandiandcode.cardsagainsthumanity.databinding.FragmentMainBinding
import com.pandiandcode.cardsagainsthumanity.viewModel.ErrorViewModel
import com.pandiandcode.cardsagainsthumanity.viewModel.MainViewModel
import org.koin.android.ext.android.inject

class MainFragment : Fragment() {
    private val mainViewModel: MainViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        FragmentMainBinding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = this@MainFragment
                viewModel = mainViewModel.also {
                    it.error.observe(viewLifecycleOwner, errorObserver)
                }
            }.root

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
}