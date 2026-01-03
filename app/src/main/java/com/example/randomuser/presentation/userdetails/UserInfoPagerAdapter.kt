package com.example.randomuser.presentation.userdetails

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.randomuser.presentation.models.details.UserDetails
import com.example.randomuser.presentation.userdetails.pages.UserContactsFragment
import com.example.randomuser.presentation.userdetails.pages.UserLocationFragment
import com.example.randomuser.presentation.userdetails.pages.UserLoginFragment
import com.example.randomuser.presentation.userdetails.pages.UserPersonalDataFragment

class UserInfoPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val userDetails: UserDetails
) : FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> UserPersonalDataFragment.newInstance(
                userDetails = userDetails
            )

            1 -> UserContactsFragment.newInstance(
                userDetails = userDetails
            )

            2 -> UserLocationFragment.newInstance(
                userDetails = userDetails
            )

            3 -> UserLoginFragment.newInstance(
                userDetails = userDetails
            )
            else -> UserPersonalDataFragment.newInstance(
                userDetails = userDetails
            )
        }
    }

    override fun getItemCount(): Int = 4
}