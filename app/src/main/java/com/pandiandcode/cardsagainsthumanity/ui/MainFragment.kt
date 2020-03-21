package com.pandiandcode.cardsagainsthumanity.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pandiandcode.cardsagainsthumanity.databinding.FragmentMainBinding
import com.pandiandcode.cardsagainsthumanity.domain.BlackDeckRepository
import com.pandiandcode.cardsagainsthumanity.domain.usecases.GetBlackCard
import com.pandiandcode.cardsagainsthumanity.viewModel.MainViewModel

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        FragmentMainBinding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = this@MainFragment
                viewModel = MainViewModel(GetBlackCard(BlackDeckRepository()))
            }.root

}