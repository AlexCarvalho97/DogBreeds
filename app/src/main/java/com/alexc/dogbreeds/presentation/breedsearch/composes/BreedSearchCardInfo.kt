package com.alexc.dogbreeds.presentation.breedsearch.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alexc.dogbreeds.ui.theme.PrimaryLight

@Composable
fun BreedSearchCardInfo(
    icon: ImageVector, info: String = "",
) {
    Row(modifier = Modifier.height(25.dp)) {
        Box(
            modifier = Modifier
                .shadow(3.dp, CircleShape, clip = true)
                .background(Color.White, CircleShape)
                .align(Alignment.CenterVertically)
        )
        {
            Icon(
                imageVector = icon,
                tint = PrimaryLight,
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(5.dp)
            )
        }

        Spacer(Modifier.height(5.dp))

        val label = info.ifBlank {
            "Unknown"
        }

        Text(
            text = label,
            color = PrimaryLight,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            modifier = Modifier
                .padding(start = 10.dp)
                .align(CenterVertically)
        )
    }
}
