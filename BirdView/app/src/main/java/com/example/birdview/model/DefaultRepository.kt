package com.example.birdview.model

import com.example.birdview.model.dto.HwaHaeDetailBody
import com.example.birdview.model.dto.HwaHaeListBody
import com.example.birdview.model.retrofit.HwaHaeApiCreator
import com.example.birdview.model.retrofit.HwaHaeApi
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

object DefaultRepository : HwaHaeRepository {

    private val service by lazy {
        HwaHaeApiCreator.create(HwaHaeApi::class.java)
    }

    override fun getList(
        skin_type: String?,
        page: Int?,
        search: String?
    ): Single<HwaHaeListBody> {
        return service.getList(skin_type, page, search)
            .subscribeOn(Schedulers.io())
    }

    override fun getDetail(id: Int?): Single<HwaHaeDetailBody> {
        return service.getDetail(id)
            .subscribeOn(Schedulers.io())
    }

}