package com.alexc.dogbreeds.common.utils

import androidx.navigation.NavController


const val BREED_LIST_SCREEN = "breed_list"
const val BREED_SEARCH_SCREEN = "breed_search"
const val BREED_DETAILS_SCREEN = "breed_details"

fun NavController.navigateToDetails(breedId: String) {
    navigate(
        BREED_DETAILS_SCREEN + "/${breedId}"
    )
}