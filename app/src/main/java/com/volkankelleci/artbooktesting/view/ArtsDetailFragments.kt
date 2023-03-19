package com.volkankelleci.artbooktesting.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    private var _binding:FragmentArtsDetailsBinding?=null
    private val binding get()=_binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentArtsDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel=ViewModelProvider(requireActivity()).get(ArtViewModel::class.java)



        subscribersObserver()

        binding.artsImageSelect.setOnClickListener {
            findNavController().navigate(ArtsDetailFragmentsDirections.actionArtsDetailFragmentsToImageApiFragments())
        }
        val callback=object :OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
        binding.saveButton.setOnClickListener {
            viewModel.makeArt(binding.nameText.text.toString(),binding.artistText.text.toString(),binding.yearText.text.toString())

        }
    }

    fun subscribersObserver(){
        viewModel.selectedImageUrls.observe(viewLifecycleOwner, Observer {url->
            _binding?.let {
                glide.load(url).into(it.artsImageSelect)
            }
        })
        viewModel.insertArtMessage.observe(viewLifecycleOwner, Observer {
            when (it.status){
                Status.SUCCESS -> {
                    Toast.makeText(requireContext(),"CALCULATED",Toast.LENGTH_LONG).show()
                    findNavController().popBackStack()
                    viewModel.resetInsertArtMsg()
                }
                Status.ERROR -> {


                }
                Status.LOADING ->{

                }
            }
        })

    }

    override fun onDestroyView() {
        _binding=null
        super.onDestroyView()
    }

}