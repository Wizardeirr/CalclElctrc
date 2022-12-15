package com.volkankelleci.artbooktesting.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.volkankelleci.artbooktesting.model.ImageResponse
import com.volkankelleci.artbooktesting.repo.ArtReporistoryInterFace
import com.volkankelleci.artbooktesting.roomdb.Art
import com.volkankelleci.artbooktesting.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ArtViewModel @Inject constructor(
    private val repository: ArtReporistoryInterFace
):ViewModel(){

    //Fragment Artda kullanılacak
    val artList=repository.getArts()

    // Image API Fragment
    private val images=MutableLiveData<Resource<ImageResponse>>()
    val imageList:LiveData<Resource<ImageResponse>>
    get() = images // bunu yapmamızın sebebi private val değiştirilemezken Mutable LD değiştirilebilir. Başka sınıflarda bunu Getter ile alınıp Setter ile düzenlenmemesi için 2tane val atıyoruz.

    private val selectedImage=MutableLiveData<String>()
    val selectedImageUrls:LiveData<String>
        get() = selectedImage

    //Art details Fragment
    private var insertArtMsg=MutableLiveData<Resource<Art>>()
    val insertArtMessage:LiveData<Resource<Art>>
    get()=  insertArtMsg



    fun resetInsertArtMsg(){
        insertArtMsg=MutableLiveData<Resource<Art>>()
    }
    fun setSelectedItems(url:String){
        selectedImage.postValue(url)
    }
    fun deleteArt(art:Art)=viewModelScope.launch {
        repository.deleteAll(art)
    }
    fun insertArt(art:Art)=viewModelScope.launch {
        repository.insertArt(art)
    }
        fun makeArt(name:String,artisName:String,year:String){
            if (name.isEmpty() || artisName.isEmpty() || year.isEmpty()){
                insertArtMsg.postValue(Resource.error("Please entry all titles",null))
                return
            }
            val yearInt=try {
                year.toInt()
            }catch (e:Exception){
                insertArtMsg.postValue(Resource.error("Please entry year as Number",null))
                return
            }
            val art=Art(name,artisName,yearInt,selectedImage.value?:"")
            insertArt(art)
            setSelectedItems("")
            insertArtMsg.postValue(Resource.success(art))
        }

    fun searchImage(imageString: String){
        if (imageString.isEmpty()){
            return
        }else{
            images.value=Resource.loading(null)
            viewModelScope.launch {
                val response=repository.imageSearch(imageString)
                images.value=response
            }
        }

    }
}



