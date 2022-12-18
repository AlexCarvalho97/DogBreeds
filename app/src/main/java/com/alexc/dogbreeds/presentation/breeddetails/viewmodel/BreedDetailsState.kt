package com.alexc.dogbreeds.presentation.breeddetails.viewmodel

import com.alexc.dogbreeds.domain.models.Breed

data class BreedDetailsState(
    val breed: Breed? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false
)
