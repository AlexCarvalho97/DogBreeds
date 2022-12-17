package com.alexc.dogbreeds.domain.models

import com.google.gson.annotations.SerializedName

data class Breed(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("breed_group")
    val breedGroup: String = "",
    @SerializedName("life_span")
    val lifeSpan: String = "",
    @SerializedName("origin")
    val origin: String = "",
    @SerializedName("weight")
    val weight: Weight = Weight(),
    @SerializedName("height")
    val height: Height = Height(),
)
