package com.example.birdview.view.index

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.birdview.model.HwaHaeListItem

@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<HwaHaeListItem>) {
    (listView.adapter as HwaHaeListAdapter).submitList(items)
}
