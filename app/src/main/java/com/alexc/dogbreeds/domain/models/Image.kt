package com.alexc.dogbreeds.domain.models

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("url") val imageUrl: String = "",
)
