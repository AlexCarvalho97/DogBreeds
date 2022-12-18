package com.alexc.dogbreeds.presentation.breedsearch.viewmodel

import com.alexc.dogbreeds.domain.models.Breed


data class BreedSearchState(
    val breedList: List<Breed> = emptyList(),
    val searchWord: String = "",
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "Something went wrong"
)

