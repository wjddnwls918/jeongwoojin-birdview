package com.example.birdview.model.retrofit

import com.example.birdview.model.dto.HwaHaeDetailBody
import com.example.birdview.model.dto.HwaHaeListBody
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HwaHaeApi {

    @GET("products")
    fun getList(
        @Query("skin_type") skin_type: String?,
        @Query("page") page: Int?,
        @Query("search") search: String?
    ): Single<HwaHaeListBody>

    @GET("products/{id}")
    fun getDetail(
        @Path("id") id: Int?
    ): Single<HwaHaeDetailBody>


}