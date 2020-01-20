package com.example.birdview.model.remote

import com.example.birdview.model.HwaHaeListItem
import retrofit2.http.GET
import retrofit2.http.Query

interface HwaHaeListApi {

    @GET("/products")
    fun getList(
        @Query("skin_type") skin_type: String,
        @Query("page") page: Int,
        @Query("search") search: String
    ): List<HwaHaeListItem>

}