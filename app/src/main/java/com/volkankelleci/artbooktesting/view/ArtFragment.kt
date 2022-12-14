package com.volkankelleci.artbooktesting.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.volkankelleci.artbooktesting.R
import com.volkankelleci.artbooktesting.databinding.FragmentArtsBinding

class ArtFragment: Fragment(R.layout.fragment_arts) {
    private var fragmentBinder:FragmentArtsBinding?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binder=FragmentArtsBinding.bind(view)
        fragmentBinder=binder
        binder.floatingActionButton.setOnClickListener {
            findNavController().navigate(ArtFragmentDirections.actionArtFragmentToArtsDetailFragments())
        }
    }

    override fun onDestroyView() {
        fragmentBinder=null
        super.onDestroyView()
    }
}