package com.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.volkankelleci.artbooktesting.roomdb.ArtDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.qualifiers.ApplicationContext


@Module
@InstallIn(ApplicationComponentManager::class)
object TestAppModule {
    @Provides
    fun injectMemoryRoom(@ApplicationContext context: Context){
        Room.inMemoryDatabaseBuilder(context,ArtDatabase::class.java).allowMainThreadQueries().build()

    }
}