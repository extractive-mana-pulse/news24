package com.example.news24.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.news24.R
import com.example.news24.databinding.ActivitySavesArticleBinding
import com.example.news24.mvvm.NewsViewModel

class SavesArticleActivity : AppCompatActivity() {

    private lateinit var noteVm: NewsViewModel
    private val binding by lazy { ActivitySavesArticleBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        noteVm = ViewModelProvider(this)[NewsViewModel::class.java]

        val title = intent.getStringExtra("Entitlement")
        val section = intent.getStringExtra("sectionInfo")
        val person = intent.getStringExtra("byPerson")
        val description = intent.getStringExtra("Explanation")
        val link = intent.getStringExtra("Connection")

        binding.apply {
            imagesSetSaves.setImageResource(R.drawable.nyt)
            titleSaves.text = title
            sectionInformation.text = section
            setAuthorName.text = person
            setDescriptionSaves.text = description
            webUrlSaves.text = link
            imagesSetAuthor.setImageResource(R.drawable.baseline_person_24)

            backFromSaves.setOnClickListener {
                onBackPressed()
            }
            sharingSaves.setOnClickListener {
                shareData()
            }
        }
    }

    private fun shareData() {
        val link = intent.getStringExtra("Connection")
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_TEXT, link)
        intent.type = "text/plain"
        startActivity(Intent.createChooser(intent,"Choose app:"))
    }
}