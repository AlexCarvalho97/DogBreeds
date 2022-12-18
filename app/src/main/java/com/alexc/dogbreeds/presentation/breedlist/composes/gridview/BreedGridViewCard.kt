package com.alexc.dogbreeds.presentation.breedlist.composes.gridview

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.alexc.dogbreeds.domain.models.Breed
import com.alexc.dogbreeds.ui.theme.QuickSand
import com.alexc.dogbreeds.ui.theme.SurfaceLight
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun BreedGridViewCard(
    breedItem: Breed,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(100.dp)
            .shadow(2.dp, shape = RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                navController.navigate(
                    "breed_details/${breedItem.id}"
                )
            }
            .background(SurfaceLight),
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(10.dp)) {
            Image(
                painter = rememberCoilPainter(request = breedItem.image.imageUrl),
                contentDescription = breedItem.name,
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                breedItem.name,
                fontFamily = QuickSand,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
    }
}