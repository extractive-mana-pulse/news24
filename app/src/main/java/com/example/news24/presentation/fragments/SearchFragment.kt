package com.example.news24.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news24.presentation.adapters.MyAdapter
import com.example.news24.presentation.constants.Constants.Companion.DELAY
import com.example.news24.presentation.constants.Constants.Companion.TAG
import com.example.news24.databinding.FragmentSearchBinding
import com.example.news24.viewModel.MainViewModel
import com.example.news24.viewModel.MainViewModelFactory
import com.example.news24.viewModel.Repository
import com.example.news24.presentation.sealed.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    private lateinit var viewModel: MainViewModel

    private val myAdapter by lazy { MyAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        var job: Job? = null

        binding.searchView.editText.setOnEditorActionListener { v, actionId, event ->
            binding.searchBar.text = binding.searchView.text
            binding.searchView.hide()
            job?.cancel()
            job = MainScope().launch {
                delay(500)
                binding.searchView.editText.let {
                    if (binding.searchView.text!!.isNotEmpty()) {
                        viewModel.searchNews(binding.searchBar.text.toString())
                    }
                }
            }
            false
        }

        binding.rcView.layoutManager = LinearLayoutManager(requireContext())
        binding.rcView.adapter = myAdapter

        viewModel.searchNews.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
//                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        myAdapter.setData(newsResponse.response.docs)
                    }
                }

                is Resource.Error -> {
//                    hideProgressBar()
                    response.message?.let { message ->
                        Toast.makeText(requireContext(), "Error: $message", Toast.LENGTH_SHORT).show()
                    }
                }

                is Resource.Loading -> {
//                    showProgressBar()
                    Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

//    private fun showProgressBar() {
//        binding.paginationProgressBar.visibility = View.VISIBLE
//    }
//
//    private fun hideProgressBar(){
//        binding.paginationProgressBar.visibility = View.INVISIBLE
//    }

    companion object {
        @JvmStatic
        fun newInstance() = SearchFragment()
    }
}