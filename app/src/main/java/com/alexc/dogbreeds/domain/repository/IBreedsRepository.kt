package com.alexc.dogbreeds.domain.repository

import com.alexc.dogbreeds.common.Resource
import com.alexc.dogbreeds.domain.models.Breed
import kotlinx.coroutines.flow.Flow

interface IBreedsRepository {

    suspend fun getBreeds(
        page: Int = 0,
        asc: Boolean = true,
        limit: Int = 20
    ): Flow<Resource<List<Breed>>>

    suspend fun getBreedsForKeyword(
        keyword: String = "",
        limit: Int = 10
    ): Flow<Resource<List<Breed>>>
}