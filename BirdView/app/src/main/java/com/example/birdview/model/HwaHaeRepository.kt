package com.example.birdview.model

import com.example.birdview.model.dto.HwaHaeDetailBody
import com.example.birdview.model.dto.HwaHaeListBody
import io.reactivex.Single

interface HwaHaeRepository {
    fun getList(
        skin_type: String?,
        page: Int?,
        search: String?
    ): Single<HwaHaeListBody>

    fun getDetail(
        id:Int?
    ) :Single<HwaHaeDetailBody>
}