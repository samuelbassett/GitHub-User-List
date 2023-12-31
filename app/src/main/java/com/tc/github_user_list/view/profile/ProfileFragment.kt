package com.tc.github_user_list.view.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.tc.github_user_list.R
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.tc.github_user_list.data.model.user.UserDetailModel
import com.tc.github_user_list.databinding.FragmentProfileBinding
import com.tc.github_user_list.util.ResponseHandling
import com.tc.github_user_list.util.Util
import com.tc.github_user_list.view.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment: Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var username: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        username = arguments?.getString("profileName")?: ""

        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPager
        val adapter = ProfilePagerAdapter(childFragmentManager, lifecycle, username)
        viewPager.adapter = adapter

        val tabLayoutMediator = TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Followers"
                1 -> tab.text = "Following"
            }
        }
        tabLayoutMediator.attach()


        profileViewModel.followerData.observe(viewLifecycleOwner) { response ->
            when(response) {
                is ResponseHandling.Success<*> -> {

                    val data = response.result
                    if (data is UserDetailModel) {
                        val followers = data.followers?.let { Util.abbreviateNumber(it) }
                        val following = data.following?.let { Util.abbreviateNumber(it) }

                        binding.ProgressBar.visibility = View.GONE
                        binding.LinearLayout.visibility = View.VISIBLE
                        binding.profileFollowers.text = followers
                        binding.profileFollowing.text = following
                        binding.profileName.text = data.name?: ""
                        binding.profileBio.text = data.bio?: ""
                        binding.profileRepositories.text = data.publicRepos.toString()
                        setToolBar(data.name?: "")

                        Glide.with(this)
                            .load(data.avatarUrl)
                            .apply(RequestOptions.circleCropTransform())
                            .error(R.drawable.ic_error)
                            .placeholder(R.drawable.ic_error)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(binding.profileImage)
                    }
                }
                is ResponseHandling.Error -> {
                    Toast.makeText(context, response.error, Toast.LENGTH_SHORT).show()
                    binding.ProgressBar.visibility = View.GONE
                    binding.LinearLayout.visibility = View.GONE
                }
                else -> {
                    ResponseHandling.Loading
                    binding.ProgressBar.visibility = View.VISIBLE
                    binding.LinearLayout.visibility = View.GONE
                }
            }

        }

        profileViewModel.getUserDetails(username)

        return root
    }

    override fun onResume() {
        super.onResume()
        setToolBar()
    }

    private fun setToolBar(name: String = username) {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = username
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}