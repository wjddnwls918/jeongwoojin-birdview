package com.example.birdview.view.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.birdview.common.Event
import com.example.birdview.model.DefaultRepository
import com.example.birdview.model.dto.HwaHaeDetailItem
import io.reactivex.disposables.CompositeDisposable

class HwaHaeDetailViewModel : ViewModel(){

    private val disposable: CompositeDisposable = CompositeDisposable()

    private val _detail = MutableLiveData<HwaHaeDetailItem>()
    val detail: LiveData<HwaHaeDetailItem> = _detail

    private val _closeDetailEvent = MutableLiveData<Event<Unit>>()
    val closeDetailEvent: LiveData<Event<Unit>> = _closeDetailEvent

    fun getDetail(id: Int?) {
        disposable.add(

            DefaultRepository.getDetail(id).subscribe({ detail ->

                detail?.let {
                    Log.d("checklist", "success detail!!")
                    //
                    _detail.postValue(it.body)
                }

            }, { e ->
                e.printStackTrace()
                Log.d("checklist","error!!!!!!!!!")
            })

        )
    }

    fun closeDetail() {
        _closeDetailEvent.value = Event(Unit)
    }

}