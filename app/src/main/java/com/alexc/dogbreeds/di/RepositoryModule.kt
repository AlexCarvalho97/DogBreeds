package com.alexc.dogbreeds.di

import com.alexc.dogbreeds.data.repository.BreedsRepository
import com.alexc.dogbreeds.domain.repository.IBreedsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindBreedRepository(
        breedsRepository: BreedsRepository
    ): IBreedsRepository
}