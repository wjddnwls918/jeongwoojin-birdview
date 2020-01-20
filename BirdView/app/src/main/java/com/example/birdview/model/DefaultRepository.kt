package com.example.birdview.model

import com.example.birdview.model.remote.HwaHaeApiCreator
import com.example.birdview.model.remote.HwaHaeListApi

class DefaultRepository : HwaHaeListRepository {

    val service: HwaHaeListApi = HwaHaeApiCreator.create(HwaHaeListApi::class.java)

    override suspend fun getList(
        skin_type: String,
        page: Int,
        search: String
    ): List<HwaHaeListItem> {
        return service.getList(skin_type, page, search)
    }

}