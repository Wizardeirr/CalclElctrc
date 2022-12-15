package com.volkankelleci.artbooktesting.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestCoordinator.RequestState
import com.volkankelleci.artbooktesting.R
import com.volkankelleci.artbooktesting.databinding.FragmentArtsDetailsBinding
import com.volkankelleci.artbooktesting.util.Status
import com.volkankelleci.artbooktesting.viewmodel.ArtViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
class ArtsDetailFragments @Inject constructor(
    val glide:RequestManager
):Fragment(R.layout.fragment_arts_details) {
    lateinit var viewModel:ArtViewModel
    private var FragmentBinder:FragmentArtsDetailsBinding?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel=ViewModelProvider(requireActivity()).get(ArtViewModel::class.java)

        val binder=FragmentArtsDetailsBinding.bind(view)
        FragmentBinder=binder

        subscribersObserver()

        binder.artsImageSelect.setOnClickListener {
            findNavController().navigate(ArtsDetailFragmentsDirections.actionArtsDetailFragmentsToImageApiFragments())
        }
        val callback=object :OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
        binder.saveButton.setOnClickListener {
            viewModel.makeArt(binder.nameText.text.toString(),binder.artistText.text.toString(),binder.yearText.text.toString())

        }
    }

    fun subscribersObserver(){
        viewModel.selectedImageUrls.observe(viewLifecycleOwner, Observer {url->
            FragmentBinder?.let {
                glide.load(url).into(it.artsImageSelect)
            }
        })
        viewModel.insertArtMessage.observe(viewLifecycleOwner, Observer {
            when (it.status){
                Status.SUCCESS -> {
                    Toast.makeText(requireContext(),"ERROR",Toast.LENGTH_LONG).show()
                    findNavController().popBackStack()
                    viewModel.resetInsertArtMsg()
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(),it.message?:"Error",Toast.LENGTH_LONG).show()

                }
                Status.LOADING ->{

                }
            }
        })

    }

    override fun onDestroyView() {
        FragmentBinder=null
        super.onDestroyView()
    }
}