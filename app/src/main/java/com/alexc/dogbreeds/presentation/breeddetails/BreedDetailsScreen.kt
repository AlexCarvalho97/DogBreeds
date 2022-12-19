package com.alexc.dogbreeds.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.alexc.dogbreeds.presentation.breeddetails.composes.BreedDetail
import com.alexc.dogbreeds.presentation.breeddetails.viewmodel.BreedDetailsViewModel
import com.alexc.dogbreeds.presentation.breedsearch.composes.InfoView
import com.alexc.dogbreeds.ui.theme.BackgroundColorLight
import com.alexc.dogbreeds.ui.theme.PrimaryLight

@Composable
fun BreedDetailsScreen(
    breedId: Int,
    navController: NavController,
    viewModel: BreedDetailsViewModel = hiltViewModel()
) {
    val state = viewModel.state

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColorLight)
    ) {
        when {
            state.breed != null -> {
                BreedDetail(state.breed, navController)
            }
            state.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.Center),
                    color = PrimaryLight
                )
            }
            state.isError -> {
                InfoView(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}