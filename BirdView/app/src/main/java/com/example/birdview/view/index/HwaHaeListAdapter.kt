package com.example.birdview.view.index

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.example.birdview.common.BaseRecyclerViewAdapter
import com.example.birdview.databinding.HwahaeListItemBinding
import com.example.birdview.model.dto.HwaHaeListItem


class HwaHaeListAdapter(dataSet: ArrayList<HwaHaeListItem>, private val viewModel: HwaHaeListViewModel) : BaseRecyclerViewAdapter<HwaHaeListItem, HwaHaeListAdapter.ViewHodler>(dataSet) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHodler {
        val binding = HwahaeListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHodler(binding)
    }

    override fun onBindView(holder: ViewHodler, position: Int) {
        holder.binding.item = getItem(position)
        holder.binding.viewmodel = viewModel
    }


    class ViewHodler(var binding: HwahaeListItemBinding) : RecyclerView.ViewHolder(binding.root)

}
