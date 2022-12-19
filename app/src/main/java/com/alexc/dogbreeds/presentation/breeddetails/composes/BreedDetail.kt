package com.alexc.dogbreeds.presentation.breeddetails.composes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.material.icons.rounded.GroupWork
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Pets
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.alexc.dogbreeds.domain.models.Breed
import com.alexc.dogbreeds.ui.theme.BackgroundColorLight
import com.alexc.dogbreeds.ui.theme.PrimaryLight
import com.google.accompanist.coil.rememberCoilPainter


@Composable
fun BreedDetail(
    breed: Breed,
    navController: NavController
) {

    LazyColumn {
        item {
            Image(
                painter = rememberCoilPainter(request = breed.image.imageUrl),
                contentDescription = "",
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop,
            )
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = (-20).dp) // Start above header image
                    .clip(
                        RoundedCornerShape(
                            topEnd = 20.dp,
                            topStart = 20.dp
                        )
                    )
                    .background(BackgroundColorLight)
                    .padding(
                        horizontal = 25.dp,
                        vertical = 20.dp
                    )
            ) {

                Text(
                    text = breed.name,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(35.dp))

                Row(
                    Modifier
                        .fillMaxSize()
                ) {

                    // Group
                    DetailsInfoCard(
                        imageVector = Icons.Rounded.GroupWork,
                        breed.breedGroup,
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(25.dp))

                    // Location
                    DetailsInfoCard(
                        imageVector = Icons.Rounded.LocationOn,
                        breed.origin,
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(25.dp))

                    // Temperament
                    DetailsInfoCard(
                        imageVector = Icons.Rounded.Pets,
                        breed.temperament,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }

    // Back icon on top of everything
    Icon(
        imageVector = Icons.Rounded.ArrowBackIos,
        contentDescription = "back",
        tint = PrimaryLight,
        modifier = Modifier
            .size(30.dp)
            .offset(16.dp, 16.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                navController.popBackStack()
            }
            .background(Color.White)
            .padding(5.dp)
    )
}