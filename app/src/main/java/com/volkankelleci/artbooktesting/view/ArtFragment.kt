package com.volkankelleci.artbooktesting.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.volkankelleci.artbooktesting.R
import com.volkankelleci.artbooktesting.adapter.ArtRecyclerAdapter
import com.volkankelleci.artbooktesting.databinding.FragmentArtsBinding
import com.volkankelleci.artbooktesting.viewmodel.ArtViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
class ArtFragment @Inject constructor(
    val artRecyclerAdapter:ArtRecyclerAdapter
): Fragment(R.layout.fragment_arts) {
    private var fragmentBinder:FragmentArtsBinding?=null
    lateinit var viewModel:ArtViewModel

    private val swipeCallback=object :ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder,
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val layoutPosition=viewHolder.layoutPosition
            val selectedArt=artRecyclerAdapter.arts[layoutPosition]
            viewModel.deleteArt(selectedArt)
        }

    }





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel=ViewModelProvider(requireActivity()).get(ArtViewModel::class.java)




        val binder=FragmentArtsBinding.bind(view)
        fragmentBinder=binder

        binder.recyclerViewArt.adapter=artRecyclerAdapter
        binder.recyclerViewArt.layoutManager=LinearLayoutManager(requireContext())
        ItemTouchHelper(swipeCallback).attachToRecyclerView(binder.recyclerViewArt)

        binder.floatingActionButton.setOnClickListener {
            findNavController().navigate(ArtFragmentDirections.actionArtFragmentToArtsDetailFragments())
        }
    }
    fun observeSubscribe(){
        viewModel.artList.observe(viewLifecycleOwner,Observer{
            artRecyclerAdapter.arts=it
        })
    }

    override fun onDestroyView() {
        fragmentBinder=null
        super.onDestroyView()
    }
}