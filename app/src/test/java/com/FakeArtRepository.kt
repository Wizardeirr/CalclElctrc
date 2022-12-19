package com

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.volkankelleci.artbooktesting.model.ImageResponse
import com.volkankelleci.artbooktesting.repo.ArtReporistoryInterFace
import com.volkankelleci.artbooktesting.repo.ArtRepository
import com.volkankelleci.artbooktesting.roomdb.Art
import com.volkankelleci.artbooktesting.util.Resource

class FakeArtRepository:ArtReporistoryInterFace{
    private val arts= mutableListOf<Art>()
    private val artsLiveData=MutableLiveData<List<Art>>(arts)
    override suspend fun insertArt(art: Art) {
        arts.add(art)
        refreshData()
    }

    override suspend fun deleteAll(art: Art) {
        arts.remove(art)
        refreshData()
    }

    override fun getArts(): LiveData<List<Art>> {
        return artsLiveData
    }

    override suspend fun imageSearch(searchQuery: String): Resource<ImageResponse> {
      return Resource.success(ImageResponse(listOf(),0,0))

    }
    private fun refreshData(){
        artsLiveData.postValue(arts)
    }
}