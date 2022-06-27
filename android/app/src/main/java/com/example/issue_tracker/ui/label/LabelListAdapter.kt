package com.example.issue_tracker.ui.label

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.issue_tracker.databinding.ItemLabelRecyclerViewBinding
import com.example.issue_tracker.model.LabelDTO

class LabelListAdapter : ListAdapter<LabelDTO, LabelListAdapter.LabelViewHolder>(LabelDiffCallback) {

    class LabelViewHolder(private val binding: ItemLabelRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(label: LabelDTO) {
            binding.label = label
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LabelViewHolder {
        val binding =
            ItemLabelRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LabelViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LabelViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        object LabelDiffCallback : DiffUtil.ItemCallback<LabelDTO>() {
            override fun areItemsTheSame(oldItem: LabelDTO, newItem: LabelDTO): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(oldItem: LabelDTO, newItem: LabelDTO): Boolean {
                return oldItem == newItem
            }
        }
    }
}