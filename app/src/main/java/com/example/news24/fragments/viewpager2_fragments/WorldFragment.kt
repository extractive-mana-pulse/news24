package com.example.news24.fragments.viewpager2_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news24.mvvm.MainViewModel
import com.example.news24.mvvm.MainViewModelFactory
import com.example.news24.mvvm.Repository
import com.example.news24.adapters.MyAdapter
import com.example.news24.constants.Constants
import com.example.news24.databinding.FragmentWorldBinding

class WorldFragment : Fragment() {

    private lateinit var binding : FragmentWorldBinding

    private lateinit var viewModel: MainViewModel

    private val myAdapter by lazy { MyAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentWorldBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // TODO
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]


        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = myAdapter

        viewModel.getNewsMiscellaneous()
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
        fun newInstance() = WorldFragment()
    }
}