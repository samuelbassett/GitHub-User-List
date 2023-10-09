package com.tc.github_user_list.view.profile.followers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tc.github_user_list.R
import com.tc.github_user_list.data.model.user.FollowersItemModel
import com.tc.github_user_list.data.model.user.UserItemModel
import com.tc.github_user_list.databinding.FragmentFollowersBinding
import com.tc.github_user_list.databinding.FragmentUsersBinding
import com.tc.github_user_list.util.ResponseHandling
import com.tc.github_user_list.view.users.UsersAdapter
import com.tc.github_user_list.view.users.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowersFragment(
    private val username: String
) : Fragment() {

    private var _binding: FragmentFollowersBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var followerAdapter: FollowersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val followersViewModel =
            ViewModelProvider(this).get(FollowersViewModel::class.java)

        _binding = FragmentFollowersBinding.inflate(inflater, container, false)
        val root: View = binding.root

        followersViewModel.followerData.observe(viewLifecycleOwner){
            when (it) {
                is ResponseHandling.Success<*> -> {
                    // Set up the RecyclerView with a LinearLayoutManager and the PeopleAdapter.
                    binding.recyclerFollowersView.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = FollowersAdapter(it.result as ArrayList<FollowersItemModel>) {

                        }
                    }
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerFollowersView.visibility = View.VISIBLE
                }

                is ResponseHandling.Error -> {
                    Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerFollowersView.visibility = View.GONE
                }

                else -> {
                    // Loading
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerFollowersView.visibility = View.GONE
                }
            }
        }

        followersViewModel.getFollowers(username)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

