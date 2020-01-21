package com.example.birdview.view.index

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.birdview.databinding.HwahaeListItemBinding
import com.example.birdview.model.HwaHaeListItem

class HwaHaeListAdapter(private val viewModel: HwaHaeListViewModel) :
    ListAdapter<HwaHaeListItem, HwaHaeListAdapter.ViewHolder>(ListDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(viewModel, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    class ViewHolder private constructor(val binding: HwahaeListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: HwaHaeListViewModel, item: HwaHaeListItem) {

            binding.viewmodel = viewModel
            binding.item = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HwahaeListItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minimum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class ListDiffCallback : DiffUtil.ItemCallback<HwaHaeListItem>() {
    override fun areItemsTheSame(oldItem: HwaHaeListItem, newItem: HwaHaeListItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: HwaHaeListItem, newItem: HwaHaeListItem): Boolean {
        return oldItem == newItem
    }
}