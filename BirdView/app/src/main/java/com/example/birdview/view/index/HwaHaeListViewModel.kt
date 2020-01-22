package com.example.birdview.view.index

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.birdview.model.DefaultRepository
import com.example.birdview.model.HwaHaeListItem
import io.reactivex.disposables.CompositeDisposable

class HwaHaeListViewModel : ViewModel(){

    private val _list = MutableLiveData<ArrayList<HwaHaeListItem>>().apply { value = arrayListOf() }
    val list: LiveData<ArrayList<HwaHaeListItem>> = _list

    private val disposable: CompositeDisposable = CompositeDisposable()

    val adapter: HwaHaeListAdapter

    init {
        adapter = HwaHaeListAdapter(arrayListOf())
        getList(null,null,null)
    }

    fun getList(
        skin_type: String?,
        page: Int?,
        search: String?
    ) {
        disposable.add(

            DefaultRepository.getList(skin_type, page, search).subscribe({ list ->

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

            }, { e ->
                e.printStackTrace()
                Log.d("checklist","error!!!!!!!!!")
            })

        )
    }

}


