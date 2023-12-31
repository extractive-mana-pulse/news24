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
import com.example.news24.presentation.constants.Constants.Companion.TAG
import com.example.news24.databinding.FragmentAutomobileBinding

class AutomobileFragment : Fragment() {

    private lateinit var binding: FragmentAutomobileBinding

    private lateinit var viewModel: MainViewModel

    private val myAdapter by lazy { MyAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    binding = FragmentAutomobileBinding.inflate(layoutInflater)
    return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        binding.recyclerView.adapter = myAdapter
        binding.recyclerView.addItemDecoration(DividerItemDecoration(binding.recyclerView.context, DividerItemDecoration.VERTICAL))
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getNewsAutomobile()
        viewModel.myResponse.observe(requireActivity()) { response ->

            if (response.isSuccessful) {
                response.body()?.let { myAdapter.setData(it.response.docs) }
            } else {
                Toast.makeText(requireContext(), TAG, Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = AutomobileFragment()
    }
}