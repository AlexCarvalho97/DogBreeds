package com.alexc.dogbreeds.presentation.breedlist.composes.bottomsheet

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.alexc.dogbreeds.ui.theme.PrimaryLight

@Composable
fun ButtonSheetButton(icon: ImageVector, isSelected: Boolean = false, onClick: () -> Unit) {

    val backgroundColor = if (isSelected) {
        PrimaryLight
    } else {
        Color.White
    }

    val iconColor = if (!isSelected) {
        PrimaryLight
    } else {
        Color.White
    }

    Button(
        onClick = {
            onClick()
        },
        modifier = Modifier.size(60.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
        shape = RoundedCornerShape(10.dp)
    ) {
        Icon(
            imageVector = icon,
            tint = iconColor,
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.CenterVertically)
        )
    }
}
