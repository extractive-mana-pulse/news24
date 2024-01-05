package com.example.news24.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news24.presentation.adapters.SavedNewsAdapter
import com.example.news24.databinding.FragmentSavedBinding
import com.example.news24.viewModel.NewsViewModel


class SavedFragment : Fragment() {

    private lateinit var binding: FragmentSavedBinding

    private val myAdapter by lazy { SavedNewsAdapter() }

    private val mainViewModel: NewsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSavedBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.rcViewSave.layoutManager = LinearLayoutManager(requireContext())

        binding.rcViewSave.adapter = myAdapter

        mainViewModel.readAllData.observe(requireActivity()) {
            myAdapter.setData(it)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SavedFragment()
    }
}