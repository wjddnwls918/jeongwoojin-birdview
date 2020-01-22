package com.example.birdview.model

import io.reactivex.Single

interface HwaHaeListRepository {
    fun getList(
        skin_type: String?,
        page: Int?,
        search: String?
    ): Single<HwaHaeListBody>
}