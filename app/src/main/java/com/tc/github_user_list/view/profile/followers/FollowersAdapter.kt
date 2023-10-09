package com.tc.github_user_list.view.profile.followers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.tc.github_user_list.R
import com.tc.github_user_list.data.model.user.FollowersItemModel
import com.tc.github_user_list.databinding.ItemFollowerBinding
import java.util.ArrayList

class FollowersAdapter(
    private val data: ArrayList<FollowersItemModel>,
    private val function: (item: FollowersItemModel) -> Unit
) :
    RecyclerView.Adapter<FollowersAdapter.CustomViewHolder>() {

    inner class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemFollowerBinding.bind(view)

        fun setupUI(userData: FollowersItemModel, position: Int) {
            binding.userName.text = userData.login
            Glide.with(view)
                .load(userData.avatarUrl)
                .apply(RequestOptions.circleCropTransform())
                .error(R.drawable.ic_error)
                .placeholder(R.drawable.ic_error)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.userImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CustomViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_follower, parent, false)
    )

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val senderData = data[position]
        holder.setupUI(senderData, position)

        holder.itemView.setOnClickListener {
            function.invoke(senderData)
        }
    }

    override fun getItemCount() = data.size
}
