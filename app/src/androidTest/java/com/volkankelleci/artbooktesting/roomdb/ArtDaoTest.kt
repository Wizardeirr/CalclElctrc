package com.volkankelleci.artbooktesting.roomdb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.volkankelleci.artbooktesting.getOrAwaitValue
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class ArtDaoTest(){
    @get:Rule
    var instantTaskExecutorRule=InstantTaskExecutorRule()
    private lateinit var dao: ArtDao
    private lateinit var dataBase:ArtDatabase
    @Before
    fun setUp(){


        dao=dataBase.Dao()
    }
    @After
    fun teardown(){
        dataBase.close()

    }
    @Test
    fun insertArtTesting()= runBlockingTest{

        val input=Art("Ahmet","Mehmet",15,"www.bomba.com",1)
        dao.insertArt(input)
        val list=dao.observeArts().getOrAwaitValue()
        assertThat(list).contains(input)

    }
    @Test
    fun deleteArtTesting()= runBlockingTest{

        val input2=Art("MONA","SISA",1900,"www.mulayim.com",2)
        dao.deleteAll(input2)
        val deleteList=dao.observeArts().getOrAwaitValue()
        assertThat(deleteList).isEmpty()
    }
}