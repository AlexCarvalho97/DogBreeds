package com.alexc.dogbreeds.presentation.breedlist.viewmodel.repository

import com.alexc.dogbreeds.common.Resource
import com.alexc.dogbreeds.domain.models.Breed
import com.alexc.dogbreeds.domain.models.Image
import com.alexc.dogbreeds.domain.repository.IBreedsRepository
import com.alexc.dogbreeds.presentation.breedlist.viewmodel.utils.getListPaginated
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MockBreedRepository : IBreedsRepository {

    val breedApiItems = mutableListOf<Breed>()
    val breedLocalItems = mutableListOf<Breed>()

    var shouldReturnNetworkItems = true
    var shouldReturnCacheItems = true

    var perPage = 2

    init {
        breedLocalItems.apply {
            add(Breed(id = "1", name = "Miniature American Shepherd"))
            add(Breed(id = "2", name = "Miniature Bull Terrier"))
        }

        breedApiItems.apply {
            add(Breed(id = "10", name = "Miniature American Shepherd"))
            add(Breed(id = "20", name = "Miniature Bull Terrier"))
            add(Breed(id = "30", name = "Akbash Dog"))
            add(Breed(id = "40", name = "Barbet"))
        }
    }

    override suspend fun getBreeds(page: Int, asc: Boolean, limit: Int): Flow<Resource<List<Breed>>> = flow {
        if (shouldReturnCacheItems && page == 0) {
            emit(Resource.Success(breedLocalItems, fromCache = true))
        }
        if (shouldReturnNetworkItems) {
            emit(Resource.Success(breedApiItems.getListPaginated(page, perPage)))
        } else {
            emit(Resource.Error("error"))
        }
    }

    override suspend fun getBreedsForKeyword(keyword: String, limit: Int): Flow<Resource<List<Breed>>> = flow {
    }

    override suspend fun getBreed(id: Int): Flow<Resource<Breed>> = flow {}

    override suspend fun getBreedImage(id: String): Image = Image()

}