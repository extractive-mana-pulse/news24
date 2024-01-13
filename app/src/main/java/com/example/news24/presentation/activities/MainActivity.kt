package com.example.news24.presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import com.example.news24.R
import com.example.news24.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val navController by lazy { (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment).navController }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.bottomNavView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home_page -> {
                    navController.navigate(R.id.mainFragment)
                    binding.topAppBar.isVisible = true
                }

                R.id.search_page -> {
                    navController.navigate(R.id.searchFragment)
                    binding.topAppBar.isVisible = false
                }

                R.id.bookmarks -> {
                    navController.navigate(R.id.savedFragment)
                    binding.topAppBar.isVisible = true
                }
            }
            return@setOnItemSelectedListener true
        }
    }
}