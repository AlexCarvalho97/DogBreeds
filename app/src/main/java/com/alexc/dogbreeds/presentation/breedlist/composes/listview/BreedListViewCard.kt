package com.alexc.dogbreeds.presentation.breedlist.composes.listview

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.alexc.dogbreeds.common.utils.navigateToDetails
import com.alexc.dogbreeds.domain.models.Breed
import com.alexc.dogbreeds.ui.theme.QuickSand
import com.alexc.dogbreeds.ui.theme.SurfaceLight
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun BreedListViewCard(
    breedItem: Breed,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier
        .fillMaxWidth()
        .shadow(2.dp, shape = RoundedCornerShape(10.dp))
        .clip(RoundedCornerShape(10.dp))
        .clickable {
            navController.navigateToDetails(breedItem.id)
        }
        .background(SurfaceLight)
    ) {

        Row(modifier = Modifier.padding(10.dp)) {
            Image(
                painter = rememberCoilPainter(request = breedItem.image.imageUrl),
                contentDescription = breedItem.name,
                modifier = Modifier
                    .aspectRatio(1.4f, true)
                    .weight(1f)
                    .clip(RoundedCornerShape(10.dp))
                    .drawWithCache {
                        val gradient = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color(0xFF2E3959)),
                            startY = size.height / 10,
                            endY = size.height
                        )
                        onDrawWithContent {
                            drawContent()
                            drawRect(gradient, blendMode = BlendMode.Multiply)
                        }
                    },
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.width(20.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    breedItem.name,
                    fontFamily = QuickSand,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}