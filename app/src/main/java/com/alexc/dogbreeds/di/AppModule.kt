package com.alexc.dogbreeds.di

import com.alexc.dogbreeds.data.remote.TheDogApi
import com.alexc.dogbreeds.data.remote.utils.ApiConstants
import com.alexc.dogbreeds.data.repository.BreedsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideBreedsRepository(
        api: TheDogApi
    ) = BreedsRepository(api)


    @Singleton
    @Provides
    fun provideTheDogApi(): TheDogApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApiConstants.API_BASE_URL)
            .build()
            .create(TheDogApi::class.java)
    }
}