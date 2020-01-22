package com.example.birdview.model.remote

import com.example.birdview.model.HwaHaeListBody
import com.example.birdview.model.HwaHaeListItem
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HwaHaeListApi {

    @GET("products")
    fun getList(
        @Query("skin_type") skin_type: String?,
        @Query("page") page: Int?,
        @Query("search") search: String?
    ): Single<HwaHaeListBody>

}