package com.viewmodel

import com.FakeArtRepository
import com.volkankelleci.artbooktesting.viewmodel.ArtViewModel
import org.junit.Before

class ArtViewModelTest {
    private lateinit var viewModel: ArtViewModel

    @Before
    fun setup(){

        viewModel= ArtViewModel(FakeArtRepository())

    }


}