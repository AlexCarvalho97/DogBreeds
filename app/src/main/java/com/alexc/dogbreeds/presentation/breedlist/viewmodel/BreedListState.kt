package com.alexc.dogbreeds.presentation.breedlist.viewmodel

import com.alexc.dogbreeds.domain.models.Breed

/**
 * Data Class that represents the breed list ui state
 */
data class BreedListState(
    val breedList: List<Breed> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String? = null,
    val listType: ListType = ListType.LIST,
    val isAscending: Boolean = true,
    var page: Int = 0
)


sealed class ListType {
    object GRID : ListType()
    object LIST : ListType()
}