package com.example.news24.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.news24.presentation.fragments.viewpager2_fragments.AutomobileFragment
import com.example.news24.presentation.fragments.viewpager2_fragments.BusinessFragment
import com.example.news24.presentation.fragments.viewpager2_fragments.NationalFragment
import com.example.news24.presentation.fragments.viewpager2_fragments.PoliticsFragment
import com.example.news24.presentation.fragments.viewpager2_fragments.ScienceFragment
import com.example.news24.presentation.fragments.viewpager2_fragments.SportsFragment
import com.example.news24.presentation.fragments.viewpager2_fragments.TechnologyFragment
import com.example.news24.presentation.fragments.viewpager2_fragments.WorldFragment

class ViewPagedAdapter(frag: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(frag, lifecycle) {
    override fun getItemCount(): Int {
        return 8
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 ->{
                // ✅
                AutomobileFragment()
            }
            1 ->{
                // ✅
                BusinessFragment()
            }
            2 ->{
                // ✅
                SportsFragment()
            }
            3 ->{
                // ✅
                WorldFragment()
            }
            4 ->{
                // ✅
                ScienceFragment()
            }
            5 ->{
                // ✅
                NationalFragment()
            }
            6 ->{
                // ✅
                TechnologyFragment()
            }
            7 ->{
                // ✅
                PoliticsFragment()
            }
            else -> {
                Fragment()
            }
        }
    }
}