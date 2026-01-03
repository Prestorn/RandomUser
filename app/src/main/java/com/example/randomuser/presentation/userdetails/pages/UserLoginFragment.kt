package com.example.randomuser.presentation.userdetails.pages

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.randomuser.databinding.FragmentUserLoginBinding
import com.example.randomuser.presentation.models.details.UserDetails
import com.example.randomuser.presentation.models.details.UserLogin

private const val USER_LOGIN_DATA_KEY = "login"

class UserLoginFragment : Fragment() {

    private var userLogin: UserLogin? = null
    private lateinit var binding: FragmentUserLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userLogin = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.getParcelable(USER_LOGIN_DATA_KEY, UserLogin::class.java)
            } else {
                @Suppress("DEPRECATION")
                it.getParcelable(USER_LOGIN_DATA_KEY)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (userLogin != null) {
            binding.username.text = userLogin!!.username
            binding.password.text = userLogin!!.password
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(userDetails: UserDetails) =
            UserLoginFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(
                        USER_LOGIN_DATA_KEY, UserLogin(
                            username = userDetails.username,
                            password = userDetails.password
                        )
                    )
                }
            }
    }
}