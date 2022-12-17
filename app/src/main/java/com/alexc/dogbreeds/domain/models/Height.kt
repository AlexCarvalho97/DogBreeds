package com.alexc.dogbreeds.domain.models

import com.google.gson.annotations.SerializedName

data class Height(
    @SerializedName("metric") val metric: String = "",
)
