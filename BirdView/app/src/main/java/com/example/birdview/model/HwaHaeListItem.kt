package com.example.birdview.model

data class HwaHaeListItem(
    val id: Int,
    val thumbnail_image: String,
    val title: String,
    val price: String,
    val oily_score: Int,
    val dry_score: Int,
    val sensitive_score: Int
)