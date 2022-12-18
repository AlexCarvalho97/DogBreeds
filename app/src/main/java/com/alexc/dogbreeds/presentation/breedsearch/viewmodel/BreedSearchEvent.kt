package com.alexc.dogbreeds.presentation.breedsearch.viewmodel

sealed class BreedSearchEvent {
    data class OnSearch(val searchWord: String = "") : BreedSearchEvent()
}
