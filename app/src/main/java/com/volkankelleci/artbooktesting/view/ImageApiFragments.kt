package com.volkankelleci.artbooktesting.view

import android.os.Bundle
import android.view.View
import android.view.ViewManager
import android.widget.GridLayout
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.volkankelleci.artbooktesting.R
import com.volkankelleci.artbooktesting.adapter.ImageRecyclerAdapter
import com.volkankelleci.artbooktesting.databinding.FragmentArtsBinding
import com.volkankelleci.artbooktesting.databinding.FragmentImageApiBinding
import com.volkankelleci.artbooktesting.roomdb.Art
import com.volkankelleci.artbooktesting.util.Status
import com.volkankelleci.artbooktesting.viewmodel.ArtViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
class ImageApiFragments@Inject constructor(
    private val imageRecyclerAdapter: ImageRecyclerAdapter
):Fragment(R.layout.fragment_image_api) {

    lateinit var viewModel: ArtViewModel
    private var imageFragmentArtsBinding:FragmentImageApiBinding?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel=ViewModelProvider(requireActivity()).get(ArtViewModel::class.java)


        val binding=FragmentImageApiBinding.bind(view)
        imageFragmentArtsBinding=binding

        var job:Job?= null
        binding.searchText.addTextChangedListener {
            job?.cancel()
            job = lifecycleScope.launch {
                delay(1000)
                it?.let {
                    if (it.toString().isNotEmpty()){
                        viewModel.searchImage(it.toString())
                    }
                }
            }
        }
        subscribeToObserver()

        binding.imageSelectRecyclerView.adapter=imageRecyclerAdapter
        //span count kaç tane görsel gösterileceği !!
        binding.imageSelectRecyclerView.layoutManager=GridLayoutManager(requireContext(),3)
        imageRecyclerAdapter.setOnItemClickListener {
            findNavController().popBackStack()
            viewModel.setSelectedItems(it)
        }




    }
    fun subscribeToObserver(){
        viewModel.imageList.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    val urls= it.data?.hits?.map { imageResult ->
                        imageResult.previewURL
                    }
                    imageRecyclerAdapter.images=urls?: listOf()
                    imageFragmentArtsBinding?.progressBar?.visibility=View.GONE

                }
                Status.LOADING ->{
                    imageFragmentArtsBinding?.progressBar?.visibility=View.VISIBLE
                }
                Status.ERROR ->{
                    Toast.makeText(requireContext(),it.message?:"Error",Toast.LENGTH_LONG).show()
                    imageFragmentArtsBinding?.progressBar?.visibility=View.GONE
                }
            }
        })
    }
}