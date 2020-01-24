package com.example.birdview.view.index

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.birdview.common.Event
import com.example.birdview.model.DefaultRepository
import com.example.birdview.model.dto.HwaHaeListItem
import io.reactivex.disposables.CompositeDisposable

class HwaHaeListViewModel : ViewModel(){

    private val _list = MutableLiveData<ArrayList<HwaHaeListItem>>().apply { value = arrayListOf() }
    val list: LiveData<ArrayList<HwaHaeListItem>> = _list

    var _queryString = MutableLiveData<String>(null)

    private val disposable: CompositeDisposable = CompositeDisposable()

    val adapter: HwaHaeListAdapter

    private val _openDetailEvent = MutableLiveData<Event<Int>>()
    val openDetailEvent: LiveData<Event<Int>> = _openDetailEvent

    val _skinType = MutableLiveData(0)

    init {
        adapter = HwaHaeListAdapter(arrayListOf(),this)
        getList(1)
    }

    fun getList(
        page: Int?
    ) {

        val trans:String? = when(_skinType.value) {
            0 -> null
            1 -> "oily"
            2 -> "dry"
            else -> "sensitive"
        }

        disposable.add(
            DefaultRepository.getList(trans, page, _queryString.value).subscribe({ list ->

                list?.let {
                    Log.d("checklist", "success!!")
                    _list.postValue(it.body)
                }

            }, { e ->
                e.printStackTrace()
                Log.d("checklist","error!!!!!!!!!")
            })

        )
    }

    fun searchList() {
        adapter.removeAllItems()
        getList(1)
    }

    fun openDetail(id:Int) {
        _openDetailEvent.value = Event(id)
    }
}


