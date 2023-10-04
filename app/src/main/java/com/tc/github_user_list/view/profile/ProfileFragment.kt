package com.tc.github_user_list.view.profile

import android.os.Bundle
import android.text.util.Linkify
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.tc.github_user_list.R
import com.tc.github_user_list.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment: Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        val image = arguments?.getString("profileImage")
        val name = arguments?.getString("profileName")
        val followersURL = arguments?.getString("profileFollowers")
        val followingURL = arguments?.getString("profileFollowing")

        Glide.with(this)
            .load(image)
            .error(R.drawable.ic_error)
            .placeholder(R.drawable.ic_error)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.imageView)

        binding.apply {
            binding.textView.text = name
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}