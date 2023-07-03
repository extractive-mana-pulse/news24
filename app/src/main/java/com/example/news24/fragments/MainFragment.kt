package com.example.news24.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.news24.adapters.ViewPagedAdapter
import com.example.news24.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = ViewPagedAdapter(childFragmentManager, lifecycle)

        binding.vp2.adapter = adapter
        TabLayoutMediator(binding.tb,binding.vp2){ tab,pos ->
            when(pos){
                0->{
                    tab.text = "national"
                }
                1->{
                    tab.text = "business"
                }
                2 ->{
                    tab.text = "sports"
                }
                3 ->{
                    tab.text = "world"
                }
                4 ->{
                    tab.text = "politics"
                }
                5 ->{
                    tab.text = "technology"
                }
                6 ->{
                    tab.text = "science"
                }
                7 ->{
                    tab.text = "automobile"
                }
            }
        }.attach()

        // TODO Continue . . .
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}