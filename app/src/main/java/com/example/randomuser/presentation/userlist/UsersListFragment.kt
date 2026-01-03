package com.example.randomuser.presentation.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.randomuser.R
import com.example.randomuser.databinding.FragmentUsersListBinding
import com.example.randomuser.presentation.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val USER_ID_KEY = "userId"
private const val FRAGMENT_SCROLL_STATE_KEY = "usersListFragment"

class UsersListFragment : Fragment() {

    private val viewModel: UsersListViewModel by viewModel<UsersListViewModel>()
    private lateinit var binding: FragmentUsersListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setListeners()

        viewModel.listState =
            (requireActivity() as MainActivity).getScrollState(FRAGMENT_SCROLL_STATE_KEY)
        if (viewModel.listState != null) {
            binding.usersList.post {
                binding.usersList.layoutManager?.onRestoreInstanceState(viewModel.listState)
            }
        }
    }

    private fun observeViewModel() {
        viewModel.usersFlow.observe(viewLifecycleOwner) {
            binding.usersList.adapter = UsersListAdapter(
                usersList = it,
                onListItemClick = ::openDetails,
                deleteUser = ::deleteUserById
            )
            binding.usersList.post {
                binding.usersList.layoutManager?.onRestoreInstanceState(viewModel.listState)
            }
        }
    }

    private fun openDetails(id: Int) {
        saveScrollState()
        val bundle = Bundle()
        bundle.putInt(USER_ID_KEY, id)
        findNavController().navigate(R.id.action_usersListFragment_to_detailsFragment, bundle)
    }

    private fun deleteUserById(id: Int) {
        viewModel.listState = binding.usersList.layoutManager?.onSaveInstanceState()
        viewModel.deleteUserById(id)
    }

    private fun setListeners() {
        binding.addButton.setOnClickListener {
            onAddButtonClick()
        }
    }

    private fun onAddButtonClick() {
        saveScrollState()
        findNavController().navigate(R.id.action_usersListFragment_to_generateFragment)
    }

    override fun onPause() {
        super.onPause()
        saveScrollState()
    }

    private fun saveScrollState() {
        val state = binding.usersList.layoutManager?.onSaveInstanceState()
        (requireActivity() as MainActivity).saveScrollState(FRAGMENT_SCROLL_STATE_KEY, state)
    }

}