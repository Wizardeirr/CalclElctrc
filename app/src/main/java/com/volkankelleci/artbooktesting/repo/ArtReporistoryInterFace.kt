package com.volkankelleci.artbooktesting.repo

import androidx.lifecycle.LiveData
import com.volkankelleci.artbooktesting.model.ImageResponse
import com.volkankelleci.artbooktesting.roomdb.Art
import com.volkankelleci.artbooktesting.util.Resource

interface ArtReporistoryInterFace{
    suspend fun insertArt(art: Art)
    suspend fun deleteAll(art:Art)
    fun getArts(): LiveData<List<Art>>
    suspend fun imageSearch(searchQuery:String):Resource<ImageResponse>
}