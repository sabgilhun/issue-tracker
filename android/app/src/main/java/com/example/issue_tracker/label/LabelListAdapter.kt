package com.example.issue_tracker.label

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.issue_tracker.Issue
import com.example.issue_tracker.Label
import com.example.issue_tracker.databinding.ItemLabelRecyclerViewBinding

class LabelListAdapter : ListAdapter<Label, LabelListAdapter.LabelViewHolder>(LabelDiffCallback) {

    class LabelViewHolder(private val binding: ItemLabelRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(label: Label) {
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
        object LabelDiffCallback : DiffUtil.ItemCallback<Label>() {
            override fun areItemsTheSame(oldItem: Label, newItem: Label): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(oldItem: Label, newItem: Label): Boolean {
                return oldItem == newItem
            }
        }
    }

}