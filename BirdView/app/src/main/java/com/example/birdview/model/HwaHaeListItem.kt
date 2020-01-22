package com.example.birdview.model

import com.google.gson.annotations.SerializedName

data class HwaHaeListItem(
    val id: Int,
    val thumbnail_image: String,
    val title: String,
    val price: String,
    @SerializedName(value="oily_score", alternate = ["dry_score", "sensitive_score"])
    val score: Int
)