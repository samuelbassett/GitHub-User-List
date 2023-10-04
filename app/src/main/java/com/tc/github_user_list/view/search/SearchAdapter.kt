package com.tc.github_user_list.view.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.tc.github_user_list.R
import com.tc.github_user_list.data.model.search.SearchItemModel
import com.tc.github_user_list.databinding.ItemSearchBinding

class SearchAdapter(
    private val data: List<SearchItemModel?>?,
    private val function: (item: SearchItemModel) -> Unit,
) : RecyclerView.Adapter<SearchAdapter.CustomViewHolder>() {

    inner class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemSearchBinding.bind(view)

        fun setupUI(searchData: SearchItemModel, position: Int) {
            binding.searchName.text = searchData.login
            Glide.with(view)
                .load(searchData.avatarUrl)
                .error(R.drawable.ic_error)
                .placeholder(R.drawable.ic_error)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.searchImage)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CustomViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
    )


    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val senderData = data?.get(position)
        if (senderData != null) {
            holder.setupUI(senderData, position)
        }

        holder.itemView.setOnClickListener {
            if (senderData != null) {
                function.invoke(senderData)
            }
        }
    }

    override fun getItemCount() = data?.size ?: 0

}
