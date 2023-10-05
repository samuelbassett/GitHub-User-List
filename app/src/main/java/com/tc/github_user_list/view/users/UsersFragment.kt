package com.tc.github_user_list.view.users

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
import com.tc.github_user_list.data.model.user.UserItemModel
import com.tc.github_user_list.databinding.FragmentUsersBinding
import com.tc.github_user_list.util.ResponseHandling
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment : Fragment() {

    private var _binding: FragmentUsersBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var userAdapter: UsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val usersViewModel =
            ViewModelProvider(this).get(UsersViewModel::class.java)

        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        val root: View = binding.root

        usersViewModel.userData.observe(viewLifecycleOwner){
            when (it) {
                is ResponseHandling.Success<*> -> {
                    // Set up the RecyclerView with a LinearLayoutManager and the PeopleAdapter.
                    binding.recyclerUsersView.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = UsersAdapter(it.result as ArrayList<UserItemModel>) {
                            findNavController().navigate(
                                R.id.action_navigation_users_to_navigation_profile,
                                bundleOf(
                                    "profileName" to it.login
                                )
                            )
                        }
                    }
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerUsersView.visibility = View.VISIBLE
                }

                is ResponseHandling.Error -> {
                    Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerUsersView.visibility = View.GONE
                }

                else -> {
                    // Loading
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerUsersView.visibility = View.GONE
                }
            }
        }


        userAdapter = UsersAdapter(arrayListOf()) {
            findNavController().navigate(
                R.id.action_navigation_users_to_navigation_profile,
                bundleOf()
            )
        }
        usersViewModel.getUsers()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}