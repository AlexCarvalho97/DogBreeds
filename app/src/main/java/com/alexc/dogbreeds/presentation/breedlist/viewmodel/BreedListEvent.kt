package com.alexc.dogbreeds.presentation.breedlist.viewmodel

sealed class BreedListEvent
{
    object OnLoadMore: BreedListEvent()
    object OnRefresh: BreedListEvent()
    data class OnChangeListType(val listType: ListType): BreedListEvent()
    object OnToggleFilter: BreedListEvent()
}
