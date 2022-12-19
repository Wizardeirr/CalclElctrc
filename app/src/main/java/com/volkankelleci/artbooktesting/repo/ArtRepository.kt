package com.volkankelleci.artbooktesting.repo

import androidx.lifecycle.LiveData
import com.volkankelleci.artbooktesting.api.RetrofitAPI
import com.volkankelleci.artbooktesting.model.ImageResponse
import com.volkankelleci.artbooktesting.roomdb.Art
import com.volkankelleci.artbooktesting.roomdb.ArtDao
import com.volkankelleci.artbooktesting.util.Resource
import javax.inject.Inject

class ArtRepository@Inject constructor(
    private val artDao:ArtDao,
    private val retrofitAPI: RetrofitAPI
):ArtReporistoryInterFace {
    override suspend fun insertArt(art: Art) {
        artDao.insertArt(art)
    }

    override suspend fun deleteAll(art: Art) {
        artDao.deleteAll(art)
    }
    override fun getArts(): LiveData<List<Art>> {
        return artDao.observeArts()
    }

    override suspend fun imageSearch(imageString: String): Resource<ImageResponse> {
        return try {
            val response=retrofitAPI.imageSearch(imageString)

            if (response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                }?:Resource.error("Error",null)
            }else{
                Resource.error("Error",null)

            }
        }catch (e:Exception){
            Resource.error("No DATA",null)
        }
    }
}