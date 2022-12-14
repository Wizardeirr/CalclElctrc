package com.volkankelleci.artbooktesting.view

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.volkankelleci.artbooktesting.R
import com.volkankelleci.artbooktesting.databinding.FragmentArtsDetailsBinding

class ArtsDetailFragments:Fragment(R.layout.fragment_arts_details) {
    private var FragmentBinder:FragmentArtsDetailsBinding?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binder=FragmentArtsDetailsBinding.bind(view)
        FragmentBinder=binder
        binder.artsImageSelect.setOnClickListener {
            findNavController().navigate(ArtsDetailFragmentsDirections.actionArtsDetailFragmentsToImageApiFragments())
        }
        val callback=object :OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    override fun onDestroyView() {
        FragmentBinder=null
        super.onDestroyView()
    }
}