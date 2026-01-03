package com.example.randomuser.presentation.userdetails

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.randomuser.R
import com.example.randomuser.databinding.FragmentDetailsBinding
import com.example.randomuser.presentation.models.details.UserDetails
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val USER_ID_KEY = "userId"
class DetailsFragment : Fragment() {


    private val viewModel: DetailsViewModel by viewModel<DetailsViewModel>()
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadUserDetails(requireArguments().getInt(USER_ID_KEY))
        observeViewModel()
        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_detailsFragment_to_usersListFragment)
        }
    }

    private fun observeViewModel() {
        viewModel.userDetails.observe(viewLifecycleOwner) {
            setUserData(it)
        }
    }

    private fun setUserData(userDetails: UserDetails) {
        Glide.with(requireContext())
            .load(userDetails.picture)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.empty_user_image)
            .into(binding.image)
        binding.name.text = "${userDetails.firstName} ${userDetails.lastName}"
        setViewPager(userDetails)
    }

    private fun setViewPager(userDetails: UserDetails) {
        binding.viewPager.adapter = UserInfoPagerAdapter(requireActivity(), userDetails)
        val tabIcons = listOf(
            R.drawable.person,
            R.drawable.phone,
            R.drawable.location,
            R.drawable.email
        )
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, pos ->
            tab.setIcon(tabIcons[pos])
        }.attach()
        setTabIconsAppearance()
    }

    private fun setTabIconsAppearance() {
        val colorStateList = ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_selected),
                intArrayOf(-android.R.attr.state_selected)
            ),
            intArrayOf(
                ContextCompat.getColor(requireContext(), R.color.dark_blue),
                ContextCompat.getColor(requireContext(), R.color.background_color)
            )
        )
        binding.tabLayout.tabIconTint = colorStateList
    }
}