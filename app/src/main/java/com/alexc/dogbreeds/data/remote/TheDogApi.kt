package com.alexc.dogbreeds.data.remote

import com.alexc.dogbreeds.domain.models.Breed
import com.alexc.dogbreeds.data.remote.utils.ApiConstants
import com.alexc.dogbreeds.domain.models.Image
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheDogApi {

    @GET(ApiConstants.API_GET_BREEDS)
    suspend fun getBreeds(
        @Query(ApiConstants.API_PAGE_QUERY) page: Int = 0,
        @Query(ApiConstants.API_ORDER_QUERY) asc: String = "asc",
        @Query(ApiConstants.API_LIMIT_QUERY) limit: Int = 20,
        @Query(ApiConstants.API_KEY_QUERY) apiKey: String = ApiConstants.API_KEY
    ): List<Breed>

    @GET(ApiConstants.API_GET_BREEDS_SEARCH)
    suspend fun getBreedsBySearch(
        @Query(ApiConstants.API_SEARCH_QUERY) query: String = "",
        @Query(ApiConstants.API_PAGE_QUERY) page: Int = 0,
        @Query(ApiConstants.API_LIMIT_QUERY) limit: Int = 20,
        @Query(ApiConstants.API_KEY_QUERY) apiKey: String = ApiConstants.API_KEY
    ): List<Breed>

    @GET(ApiConstants.API_GET_BREEDS + "{breedId}")
    suspend fun getBreedById(
        @Path("breedId") searchTerm: Int,
        @Query(ApiConstants.API_KEY_QUERY) apiKey: String = ApiConstants.API_KEY
    ): Breed

    @GET(ApiConstants.API_GET_IMAGES + "{imageId}")
    suspend fun getBreedImage(
        @Path("imageId") imageId: String,
        @Query(ApiConstants.API_KEY_QUERY) apiKey: String = ApiConstants.API_KEY
    ): Image
}