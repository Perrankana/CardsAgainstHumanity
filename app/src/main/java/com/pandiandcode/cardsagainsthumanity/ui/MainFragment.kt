package com.pandiandcode.cardsagainsthumanity.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pandiandcode.cardsagainsthumanity.databinding.FragmentMainBinding
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
                viewModel = mainViewModel
            }.root

}