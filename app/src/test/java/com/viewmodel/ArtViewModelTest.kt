package com.viewmodel

import com.FakeArtRepository
import com.volkankelleci.artbooktesting.viewmodel.ArtViewModel
import org.junit.Before
import org.junit.Test

class ArtViewModelTest {
    private lateinit var viewModel: ArtViewModel

    @Before
    fun setup() {

        viewModel = ArtViewModel(FakeArtRepository())

    }

    @Test
        //makeart fun tests
    fun withoutName(){
        viewModel.makeArt("","YES","BUZ")

    }
}