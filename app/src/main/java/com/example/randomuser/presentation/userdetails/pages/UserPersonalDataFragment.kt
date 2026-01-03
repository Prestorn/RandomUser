package com.example.randomuser.presentation.userdetails.pages

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.randomuser.databinding.FragmentUserPersonalDataBinding
import com.example.randomuser.presentation.models.details.UserDetails
import com.example.randomuser.presentation.models.details.UserPersonalData
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

private const val USER_PERSONAL_DATA_KEY = "personal"

class UserPersonalDataFragment : Fragment() {
    private var personalData: UserPersonalData? = null
    private lateinit var binding: FragmentUserPersonalDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            personalData =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    it.getParcelable(USER_PERSONAL_DATA_KEY, UserPersonalData::class.java)
                } else {
                    @Suppress("DEPRECATION")
                    it.getParcelable(USER_PERSONAL_DATA_KEY)
                }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserPersonalDataBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (personalData != null) {
            binding.firstName.text = personalData!!.firstName
            binding.lastName.text = personalData!!.lastName
            binding.gender.text = personalData!!.gender
            binding.age.text = personalData!!.age.toString()
            binding.dob.text = personalData!!.dateOfBirth
        }

    }

    companion object {

        @JvmStatic
        fun newInstance(userDetails: UserDetails): UserPersonalDataFragment =
            UserPersonalDataFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(USER_PERSONAL_DATA_KEY, UserPersonalData(
                        firstName = userDetails.firstName,
                        lastName = userDetails.lastName,
                        gender = userDetails.gender.substring(0, 1)
                            .uppercase() + userDetails.gender.substring(1),
                        dateOfBirth = userDetails.birthDate,
                        age = calculateYears(userDetails.birthDate)
                    ))
                }
            }

        private fun calculateYears(dateOfBirthString: String): Int {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val dateOfBirth = LocalDate.parse(dateOfBirthString, formatter)

            return Period.between(dateOfBirth, LocalDate.now()).years
        }
    }
}