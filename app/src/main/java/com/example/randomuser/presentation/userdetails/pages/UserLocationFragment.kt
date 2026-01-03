package com.example.randomuser.presentation.userdetails.pages

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.randomuser.databinding.FragmentUserLocationBinding
import com.example.randomuser.presentation.models.details.UserDetails
import com.example.randomuser.presentation.models.details.UserLocation

private const val USER_LOCATION_DATA = "location"

class UserLocationFragment : Fragment() {

    private var userLocation: UserLocation? = null
    private lateinit var binding: FragmentUserLocationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userLocation =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    it.getParcelable(USER_LOCATION_DATA, UserLocation::class.java)
                } else {
                    @Suppress("DEPRECATION")
                    it.getParcelable(USER_LOCATION_DATA)
                }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserLocationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (userLocation != null) {
            binding.country.text = userLocation!!.country
            binding.state.text = userLocation!!.state
            binding.city.text = userLocation!!.city
            binding.streetName.text = userLocation!!.streetName
            binding.streetNumber.text = userLocation!!.streetNumber.toString()
            binding.postcode.text = userLocation!!.postCode
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(userDetails: UserDetails) =
            UserLocationFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(
                        USER_LOCATION_DATA, UserLocation(
                            country = userDetails.country,
                            state = userDetails.state,
                            city = userDetails.city,
                            streetName = userDetails.streetName,
                            streetNumber = userDetails.streetNumber,
                            postCode = userDetails.postcode
                        )
                    )
                }
            }
    }
}