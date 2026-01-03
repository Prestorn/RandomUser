package com.example.randomuser.presentation.generateuser

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.randomuser.R
import com.example.randomuser.databinding.FragmentGenerateBinding
import com.example.randomuser.domain.models.GenerateParams
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val USER_ID_KEY = "userId"
class GenerateFragment : Fragment() {

    private lateinit var binding: FragmentGenerateBinding
    private val viewModel: GenerateViewModel by viewModel<GenerateViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGenerateBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSpinners()
        setListeners()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.generatedUserId.observe(viewLifecycleOwner) {
            hideLoading()
            if (it != -1) {
                val bundle = Bundle()
                bundle.putInt(USER_ID_KEY, it)
                findNavController().navigate(R.id.action_generateFragment_to_detailsFragment, bundle)
            } else {
                Toast.makeText(requireContext(), "Нет связи с сервером", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setSpinners() {
        val genderAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.genders,
            R.layout.spinner_item
        ).apply {
            setDropDownViewResource(R.layout.spinner_item)
        }
        val nationalityAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.nationalities,
            R.layout.spinner_item
        ).apply {
            setDropDownViewResource(R.layout.spinner_item)
        }

        binding.genderSpinner.adapter = genderAdapter
        binding.nationalitySpinner.adapter = nationalityAdapter
    }

    private fun setListeners() {
        binding.generateButton.setOnClickListener {
            onGenerateButtonClick()
        }
        binding.backButton.setOnClickListener {
            onBackButtonClick()
        }
    }

    private fun onGenerateButtonClick() {
        showLoading()
        viewModel.generateUser(
            params = GenerateParams(
                gender = binding.genderSpinner.selectedItem as String,
                nationality = binding.nationalitySpinner.selectedItem as String
            )
        )
    }

    private fun onBackButtonClick() {
        findNavController().navigate(R.id.action_generateFragment_to_usersListFragment)
    }

    private fun showLoading() {
        binding.loadingBackground.visibility = View.VISIBLE
        binding.genderSpinner.isEnabled = false
        binding.nationalitySpinner.isEnabled = false
        binding.generateButton.isEnabled = false
    }

    private fun hideLoading() {
        binding.loadingBackground.visibility = View.GONE
        binding.genderSpinner.isEnabled = true
        binding.nationalitySpinner.isEnabled = true
        binding.generateButton.isEnabled = true
    }
}