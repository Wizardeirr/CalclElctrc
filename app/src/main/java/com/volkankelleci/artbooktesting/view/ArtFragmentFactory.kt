package com.volkankelleci.artbooktesting.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.volkankelleci.artbooktesting.adapter.ArtRecyclerAdapter
import com.volkankelleci.artbooktesting.adapter.ImageRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
class ArtFragmentFactory @Inject constructor(
    private val artRecyclerAdapter: ArtRecyclerAdapter,
    private val glide: RequestManager,
    private val imageRecyclerAdapter: ImageRecyclerAdapter,
) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when (className) {
            ArtFragment::class.java.name -> ArtFragment(artRecyclerAdapter)
            ImageApiFragments::class.java.name -> ImageApiFragments(imageRecyclerAdapter)
            ArtsDetailFragments::class.java.name -> ArtsDetailFragments(glide)
            else -> super.instantiate(classLoader, className)
        }

    }
}