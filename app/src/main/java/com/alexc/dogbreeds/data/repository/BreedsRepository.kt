package com.alexc.dogbreeds.data.repository

import com.alexc.dogbreeds.common.Resource
import com.alexc.dogbreeds.domain.models.Breed
import com.alexc.dogbreeds.data.remote.TheDogApi
import com.alexc.dogbreeds.domain.repository.IBreedsRepository
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


@ActivityScoped
class BreedsRepository @Inject constructor(
    private val api: TheDogApi
) : IBreedsRepository {

    override suspend fun getBreeds(
        page: Int,
        limit: Int
    ): Flow<Resource<List<Breed>>> = flow {
        val response = try {
            api.getBreeds(page, limit)
        } catch (e: Exception) {
            emit(Resource.Error("An error occurred while obtaining breeds"))
            return@flow
        }

        emit(Resource.Success(response))
    }
}