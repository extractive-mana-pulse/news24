package com.example.news24.presentation.fragments.viewpager2_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news24.viewModel.MainViewModel
import com.example.news24.viewModel.MainViewModelFactory
import com.example.news24.viewModel.Repository
import com.example.news24.presentation.adapters.MyAdapter
import com.example.news24.presentation.constants.Constants
import com.example.news24.databinding.FragmentNationalBinding

class NationalFragment : Fragment() {

    private lateinit var binding: FragmentNationalBinding

    private lateinit var viewModel: MainViewModel

    private val myAdapter by lazy { MyAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNationalBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = myAdapter

        viewModel.getNewsNational()
        binding.recyclerView.addItemDecoration(DividerItemDecoration(binding.recyclerView.context, DividerItemDecoration.VERTICAL))
        viewModel.myResponse.observe(requireActivity()) { response ->
            if (response.isSuccessful) {
                response.body()?.let { myAdapter.setData(it.response.docs) }
            } else {
                Toast.makeText(requireContext(), Constants.TAG, Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = NationalFragment()
    }
}