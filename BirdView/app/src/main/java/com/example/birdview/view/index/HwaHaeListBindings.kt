package com.example.birdview.view.index

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.birdview.model.HwaHaeListItem

object HwaHaeListBindings {

    @JvmStatic
    @BindingAdapter("items")
    fun setItems(listView: RecyclerView, items: ArrayList<HwaHaeListItem>) {

        Log.d("checklist","binding adapter item size : " + items.size.toString())

        var adapter: HwaHaeListAdapter

        when (listView.adapter) {
            null -> {
                adapter = HwaHaeListAdapter(arrayListOf())
                listView.adapter= adapter
                adapter.updateItems(items)
            }
            else -> {
                adapter = listView.adapter as HwaHaeListAdapter
                adapter.updateItems(items)
            }
        }

    }

    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(imageView: ImageView, thumbnail_image: String) {
        Glide.with(imageView.context).load(thumbnail_image).into(imageView)
    }

}