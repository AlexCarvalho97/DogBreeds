package com.alexc.dogbreeds.presentation.breedsearch.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.GroupWork
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.alexc.dogbreeds.domain.models.Breed
import com.alexc.dogbreeds.ui.theme.QuickSand
import com.alexc.dogbreeds.ui.theme.SurfaceLight

@Composable
fun BreedSearchCard(
    breedItem: Breed,
    navController: NavController,
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier
        .fillMaxWidth()
        .shadow(2.dp, shape = RoundedCornerShape(10.dp))
        .clip(RoundedCornerShape(10.dp))
        .clickable {
            // TODO navigate to details
        }
        .background(SurfaceLight)
    ) {

        Column(modifier = Modifier.padding(15.dp)) {
            Text(
                breedItem.name,
                fontFamily = QuickSand,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(10.dp))

            BreedSearchCardInfo(Icons.Rounded.GroupWork, breedItem.breedGroup)

            Spacer(modifier = Modifier.height(10.dp))

            BreedSearchCardInfo(Icons.Rounded.LocationOn, breedItem.origin)
        }
    }

}