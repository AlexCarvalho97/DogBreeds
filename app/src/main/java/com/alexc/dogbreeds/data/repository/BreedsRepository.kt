package com.alexc.dogbreeds.data.repository

import android.util.Log
import com.alexc.dogbreeds.common.Resource
import com.alexc.dogbreeds.common.utils.toBreed
import com.alexc.dogbreeds.common.utils.toBreedEntity
import com.alexc.dogbreeds.data.local.BreedDatabase
import com.alexc.dogbreeds.domain.models.Breed
import com.alexc.dogbreeds.data.remote.TheDogApi
import com.alexc.dogbreeds.domain.repository.IBreedsRepository
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


@ActivityScoped
class BreedsRepository @Inject constructor(
    private val api: TheDogApi,
    private val db: BreedDatabase
) : IBreedsRepository {

    private val dao = db.dao

    override suspend fun getBreeds(
        page: Int,
        asc: Boolean,
        limit: Int
    ): Flow<Resource<List<Breed>>> = flow {

        emit(Resource.Loading(true))

        // Get breeds from cache sorted
        if (page == 0) {
            val cachedBreeds = dao.getBreeds()
            val sortedBreeds = if (asc) {
                cachedBreeds.sortedBy {
                    it.name
                }
            } else {
                cachedBreeds.sortedBy {
                    it.name
                }
            }

            emit(
                Resource.Success(
                    data = sortedBreeds.map { it.toBreed() },
                    fromCache = true
            ))
        }

        // Get breeds from network
        val response = try {
            val order = if (asc) "asc" else "desc"
            api.getBreeds(page, order, limit)
        } catch (e: Exception) {
            emit(Resource.Loading(false))
            emit(Resource.Error("An error occurred while obtaining breeds"))
            return@flow
        }

        emit(Resource.Loading(false))
        emit(Resource.Success(response))

        // Update cache
        response.map {
            it.toBreedEntity()
        }.let {
            dao.insertBreeds(it)
        }
    }
}