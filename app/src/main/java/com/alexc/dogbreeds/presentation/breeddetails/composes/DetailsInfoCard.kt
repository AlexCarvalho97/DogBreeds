package com.alexc.dogbreeds.presentation.breeddetails.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alexc.dogbreeds.ui.theme.PrimaryLight
import com.alexc.dogbreeds.ui.theme.QuickSand


@Composable
fun DetailsInfoCard(
    imageVector: ImageVector,
    info: String? = "",
    modifier: Modifier = Modifier
) {
    val _info = if (info.isNullOrEmpty()) {
        "Unknown"
    } else {
        info
    }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(PrimaryLight)
            .padding(10.dp)
            .aspectRatio(
                0.6f,
                matchHeightConstraintsFirst = false
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(
            imageVector = imageVector,
            tint = PrimaryLight,
            contentDescription = "",
            modifier = Modifier
                .background(
                    Color.White,
                    CircleShape
                )
                .padding(5.dp)

        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = _info,
            color = Color.White,
            fontFamily = QuickSand,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
    }
}