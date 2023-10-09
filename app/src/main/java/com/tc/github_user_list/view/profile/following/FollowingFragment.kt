package com.tc.github_user_list.view.profile.following

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tc.github_user_list.data.model.user.FollowersItemModel
import com.tc.github_user_list.data.model.user.FollowingItemModel
import com.tc.github_user_list.databinding.FragmentFollowersBinding
import com.tc.github_user_list.databinding.FragmentFollowingBinding
import com.tc.github_user_list.util.ResponseHandling
import com.tc.github_user_list.view.profile.followers.FollowersAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowingFragment(
    private val username: String
) : Fragment() {

    private var _binding: FragmentFollowingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var followerAdapter: FollowingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val followingViewModel =
            ViewModelProvider(this).get(FollowingViewModel::class.java)

        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        val root: View = binding.root

        followingViewModel.followingData.observe(viewLifecycleOwner){
            when (it) {
                is ResponseHandling.Success<*> -> {
                    // Set up the RecyclerView with a LinearLayoutManager and the PeopleAdapter.
                    binding.recyclerFollowingView.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = FollowingAdapter(it.result as ArrayList<FollowingItemModel>) {

                        }
                    }
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerFollowingView.visibility = View.VISIBLE
                }

                is ResponseHandling.Error -> {
                    Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerFollowingView.visibility = View.GONE
                }

                else -> {
                    // Loading
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerFollowingView.visibility = View.GONE
                }
            }
        }

        followingViewModel.getFollowing(username)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
