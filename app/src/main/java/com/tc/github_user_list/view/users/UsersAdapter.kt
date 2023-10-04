package com.tc.github_user_list.view.users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.tc.github_user_list.R
import com.tc.github_user_list.data.model.user.UserItemModel
import com.tc.github_user_list.databinding.ItemUserBinding
import java.util.ArrayList

class UsersAdapter(
    private val data: ArrayList<UserItemModel>,
    private val function: (item: UserItemModel) -> Unit
) :
    RecyclerView.Adapter<UsersAdapter.CustomViewHolder>() {

    inner class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemUserBinding.bind(view)

        fun setupUI(userData: UserItemModel, position: Int) {
            binding.userName.text = userData.login
            Glide.with(view)
                .load(userData.avatarUrl)
                .error(R.drawable.ic_error)
                .placeholder(R.drawable.ic_error)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.userImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CustomViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
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
