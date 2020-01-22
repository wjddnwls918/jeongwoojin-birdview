package com.example.birdview.view.index

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.birdview.common.Event
import com.example.birdview.model.DefaultRepository
import com.example.birdview.model.HwaHaeListItem
import io.reactivex.disposables.CompositeDisposable

class HwaHaeListViewModel : ViewModel(){

    private val _list = MutableLiveData<ArrayList<HwaHaeListItem>>().apply { value = arrayListOf() }
    val list: LiveData<ArrayList<HwaHaeListItem>> = _list

    var _queryString = MutableLiveData<String>(null)

    private val disposable: CompositeDisposable = CompositeDisposable()

    val adapter: HwaHaeListAdapter

    private val _openDetailEvent = MutableLiveData<Event<Int>>()
    val openDetailEvent: LiveData<Event<Int>> = _openDetailEvent


    init {
        adapter = HwaHaeListAdapter(arrayListOf(),this)
        getList(null,null)
    }

    fun getList(
        skin_type: String?,
        page: Int?

    ) {
        disposable.add(

            DefaultRepository.getList(skin_type, page, _queryString.value).subscribe({ list ->

                list?.let {
                    Log.d("checklist", "success!!")
                    Log.d("checklist", it.body.size.toString())
                    Log.d("checklist", it.body[0].id.toString())
                    Log.d("checklist", it.body[0].title)
                    Log.d("checklist", it.body[0].price)
                    Log.d("checklist", it.body[0].thumbnail_image)
                    Log.d("checklist", it.body[0].score.toString())
                    //
                    _list.postValue(it.body)
                }

                if(list.statusCode == 404)
                    Log.d("checklist","검색 결과가 없습니다")

            }, { e ->
                e.printStackTrace()
                Log.d("checklist","error!!!!!!!!!")

            })

        )
    }

    fun searchList(query:String?) {
        adapter.removeAllItems()
        getList(null, null)
    }

    fun addList(list : ArrayList<HwaHaeListItem>) {
        adapter.addItems(list)
    }

    fun openDetail(id:Int) {
        _openDetailEvent.value = Event(id)
    }
}


