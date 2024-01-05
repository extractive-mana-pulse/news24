package com.example.news24.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.news24.R
import com.example.news24.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var binding : FragmentSettingsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSettingsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            backSettingsBtn.setOnClickListener {
                requireActivity().onBackPressed()
            }
            languageSettings.setOnClickListener {
                Toast.makeText(requireContext(), "multi language stuff", Toast.LENGTH_SHORT).show()
            }
            modeSettings.setOnClickListener {
                Toast.makeText(requireContext(), "light mode || dark mode manually", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SettingsFragment()
    }
}