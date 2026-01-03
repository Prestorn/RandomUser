package com.example.randomuser.presentation.userdetails.pages

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.randomuser.databinding.FragmentUserContactsBinding
import com.example.randomuser.presentation.models.details.UserContacts
import com.example.randomuser.presentation.models.details.UserDetails

private const val USER_CONTACTS_KEY = "contacts"

class UserContactsFragment : Fragment() {

    private var userContacts: UserContacts? = null
    private lateinit var binding: FragmentUserContactsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userContacts =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    it.getParcelable(USER_CONTACTS_KEY, UserContacts::class.java)
                } else {
                    @Suppress("DEPRECATION")
                    it.getParcelable(USER_CONTACTS_KEY)
                }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserContactsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (userContacts != null) {
            binding.phone.text = userContacts!!.telephoneNumber
            binding.email.text = userContacts!!.email
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(userDetails: UserDetails) =
            UserContactsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(
                        USER_CONTACTS_KEY, UserContacts(
                            email = userDetails.email,
                            telephoneNumber = userDetails.telephoneNumber
                        )
                    )
                }
            }
    }
}