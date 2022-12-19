package com.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.FakeArtRepository
import com.google.common.truth.Truth.assertThat
import com.volkankelleci.artbooktesting.MainCoroutineRule
import com.volkankelleci.artbooktesting.util.Status
import com.volkankelleci.artbooktesting.viewmodel.ArtViewModel
import com.volkankelleci.getOrAwaitValueTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ArtViewModelTest {

    @get:Rule
    var instantTaskExecutorRule=InstantTaskExecutorRule()
    @get:Rule
    var mainCoRoutineRule=MainCoroutineRule()
    private lateinit var viewModel: ArtViewModel


    @Before
    fun setup() {

        viewModel = ArtViewModel(FakeArtRepository())

    }

    @Test
        //makeart fun tests
    fun withoutName(){
        viewModel.makeArt("","YES","BUZ")
        val value=viewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)

    }
}