package com.example.birdview.view.index

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.birdview.model.DefaultRepository
import com.example.birdview.model.HwaHaeListItem
import io.reactivex.disposables.CompositeDisposable

class HwaHaeListViewModel(
    application: Application

) : AndroidViewModel(application) {

    private val _list = MutableLiveData<List<HwaHaeListItem>>().apply { value = emptyList() }
    val list: LiveData<List<HwaHaeListItem>> = _list

    private val disposable: CompositeDisposable = CompositeDisposable()

    fun getList(
        skin_type: String?,
        page: Int?,
        search: String?
    ) {
        disposable.add(

            DefaultRepository.getList(skin_type, page, search).subscribe({ list ->

                Log.d("checklist","success!!")
                _list.postValue(list)

            }, { e ->
                e.printStackTrace()
                Log.d("checklist","error!!!!!!!!!")
            })

        )
    }

}


