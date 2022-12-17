package com.alexc.dogbreeds.domain.repository

import com.alexc.dogbreeds.common.Resource
import com.alexc.dogbreeds.domain.models.Breed
import kotlinx.coroutines.flow.Flow

interface IBreedsRepository {

    suspend fun getBreeds(
        page: Int = 0,
        limit: Int = 20
    ): Flow<Resource<List<Breed>>>

}