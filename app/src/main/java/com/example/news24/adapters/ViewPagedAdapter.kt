package com.example.news24.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.news24.fragments.viewpager2_fragments.*

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