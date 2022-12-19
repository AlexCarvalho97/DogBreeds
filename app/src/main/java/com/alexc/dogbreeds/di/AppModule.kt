package com.alexc.dogbreeds.di

import android.app.Application
import androidx.room.Room
import com.alexc.dogbreeds.data.local.BreedDatabase
import com.alexc.dogbreeds.data.remote.TheDogApi
import com.alexc.dogbreeds.data.remote.utils.ApiConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideTheDogApi(): TheDogApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApiConstants.API_BASE_URL)
            .build()
            .create(TheDogApi::class.java)
    }


    @Provides
    @Singleton
    fun provideBreedDatabase(app: Application): BreedDatabase {
        return Room.databaseBuilder(
            app, BreedDatabase::class.java, "bread.db"
        ).fallbackToDestructiveMigration().build()
    }
}