package com.alexc.dogbreeds.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BreedEntity(
    @PrimaryKey val id: String,
    val name: String,
    val breedGroup: String,
    val lifeSpan: String,
    val weight: String,
    val origin: String,
    val height: String,
    val imageUrl: String
)