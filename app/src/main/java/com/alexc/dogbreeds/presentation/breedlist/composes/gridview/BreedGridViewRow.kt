package com.alexc.dogbreeds.presentation.breedlist.composes.gridview

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.alexc.dogbreeds.domain.models.Breed


@Composable
fun BreedGridViewRow(
    rowIndex: Int,
    breedList: List<Breed>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column {
        Row {
            BreedGridViewCard(
                breedItem = breedList[rowIndex * 2],
                navController = navController,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            if (breedList.size >= rowIndex * 2 + 2) {
                BreedGridViewCard(
                    breedItem = breedList[rowIndex * 2 + 1],
                    navController = navController,
                    modifier = Modifier.weight(1f)
                )
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }

}