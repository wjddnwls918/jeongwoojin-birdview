package com.example.birdview.model

interface HwaHaeListRepository {
    suspend fun getList(
        skin_type: String,
        page: Int,
        search: String
    ): List<HwaHaeListItem>
}