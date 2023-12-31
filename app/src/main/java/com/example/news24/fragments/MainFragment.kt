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
        TabLayoutMediator(binding.tabLayout,binding.vp2){ tab,pos ->
            when(pos){
                // TODO { add this code: resources.getText(R.string.national).
                //      purpose: make multi language app so when user change language titles also change
                //  }
                0->{
                    tab.text = "National"
                }
                1->{
                    tab.text = "Business"
                }
                2 ->{
                    tab.text = "Sport"
                }
                3 ->{
                    tab.text = "World News"
                }
                4 ->{
                    tab.text = "Politics"
                }
                5 ->{
                    tab.text = "Technology"
                }
                6 ->{
                    tab.text = "Science"
                }
                7 ->{
                    tab.text = "Automobile"
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