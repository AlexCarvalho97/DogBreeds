package com.alexc.dogbreeds.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.alexc.dogbreeds.domain.models.Breed
import com.alexc.dogbreeds.presentation.breeddetails.viewmodel.BreedDetailsViewModel
import com.alexc.dogbreeds.ui.theme.BackgroundColorLight
import com.alexc.dogbreeds.ui.theme.PrimaryLight
import com.google.accompanist.coil.rememberCoilPainter


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
            }
            state.isError -> {
            }
        }
    }
}


@Composable
fun BreedDetail(
    breed: Breed,
    navController: NavController
) {

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        item {
            Image(
                painter = rememberCoilPainter(request = breed.image.imageUrl),
                contentDescription = "",
                modifier = Modifier
                    .height(400.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop,
            )
        }

        item {
            Text(
                text = breed.name,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            )
        }
    }

    Icon(
        imageVector = Icons.Rounded.ArrowBackIos,
        contentDescription = "back",
        tint = PrimaryLight,
        modifier = Modifier
            .size(30.dp)
            .offset(16.dp, 16.dp)
            .clickable {
                navController.popBackStack()
            }
    )
}