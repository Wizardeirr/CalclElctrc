package com.volkankelleci.artbooktesting.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.volkankelleci.artbooktesting.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint //Mutlaka eklenmesi gereken anotation for use to Hilt
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var fragmentFactory:ArtFragmentFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory=fragmentFactory
        setContentView(R.layout.activity_main)
    }
}