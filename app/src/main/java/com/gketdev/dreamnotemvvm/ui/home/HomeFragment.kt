package com.gketdev.dreamnotemvvm.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.gketdev.dreamnotemvvm.adapter.NoteAdapter
import com.gketdev.dreamnotemvvm.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private val adapter = NoteAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewNote.adapter = adapter
        observeItem()
        addNote()
        clearNotes()
    }

    private fun observeItem() {
        lifecycleScope.launchWhenCreated {
            viewModel.viewState.collect {
                when (it) {
                    is HomeViewState.AllNotes -> {
                        adapter.items = it.list.asReversed()
                        if (binding.textViewEmptyList.isVisible)
                            binding.textViewEmptyList.visibility = View.GONE
                    }
                    is HomeViewState.EmptyList -> {
                        binding.textViewEmptyList.visibility = View.VISIBLE
                        adapter.items = emptyList()
                    }
                    is HomeViewState.Error -> {
                        Toast.makeText(requireContext(), it.error.toString(), Toast.LENGTH_SHORT)
                            .show()
                    }
                    is HomeViewState.Loading -> {
                        Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    private fun addNote() {
        binding.floatingActionButton.setOnClickListener {
            viewModel.getNewNotes()
        }
    }

    private fun clearNotes() {
        binding.buttonClear.setOnClickListener {
            viewModel.deleteNotes()
        }
    }
}

