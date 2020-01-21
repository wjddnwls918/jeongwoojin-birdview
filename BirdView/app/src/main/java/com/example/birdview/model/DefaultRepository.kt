package com.example.birdview.model

import com.example.birdview.model.remote.HwaHaeApiCreator
import com.example.birdview.model.remote.HwaHaeListApi
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

object DefaultRepository : HwaHaeListRepository {

    private val service by lazy {
        HwaHaeApiCreator.create(HwaHaeListApi::class.java)
    }

    override fun getList(
        skin_type: String?,
        page: Int?,
        search: String?
    ): Single<List<HwaHaeListItem>> {
        return service.getList(skin_type, page, search)
            .subscribeOn(Schedulers.io())
    }

}