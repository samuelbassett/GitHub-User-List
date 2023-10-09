package com.tc.github_user_list.view.profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tc.github_user_list.view.profile.followers.FollowersFragment
import com.tc.github_user_list.view.profile.following.FollowingFragment

class ProfilePagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, username: String) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    val username = username

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FollowersFragment(username)
            1 -> FollowingFragment(username)
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}
