package com.vmn.aisle.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vmn.aisle.R
import com.vmn.aisle.core.BaseFragment
import com.vmn.aisle.databinding.FragmentNotesBinding
import com.vmn.aisle.home.adapter.InvitesAdapter
import com.vmn.aisle.home.adapter.LikesAdapter
import com.vmn.aisle.home.viewmodel.NotesViewModel
import com.vmn.domain.utils.Resource
import org.koin.android.ext.android.inject

class NotesFragment : BaseFragment() {

    private lateinit var binding: FragmentNotesBinding
    private val notesViewModel by inject<NotesViewModel>()
    private val invitesAdapter = InvitesAdapter()
    private val likesAdapter = LikesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNotesBinding.inflate(inflater, container, false)
        binding.viewModel = notesViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOps()
        observers()
        listeners()
    }

    private fun initOps() {
        notesViewModel.getNotesDetails()

        binding.rvInvites.apply {
            adapter = invitesAdapter
        }

        binding.rvLikes.apply {
            adapter = likesAdapter
        }
    }

    private fun observers() {

        notesViewModel.details.observe(viewLifecycleOwner) {
            it?.let { res ->
                when (res) {
                    is Resource.Success -> {
                        invitesAdapter.updateData(res.data?.invites?.profiles)
                        likesAdapter.updateData(res.data?.likes?.profiles)
                    }

                    is Resource.Error -> {
                        showShortMessage(res.errorMessage)
                    }

                    Resource.NoInternet -> {
                        showShortMessage(getString(R.string.no_internet))
                    }

                    Resource.Unknown -> {
                        showShortMessage(getString(R.string.error_unknown))
                    }
                }

            }
        }

    }

    private fun listeners() {

    }

}