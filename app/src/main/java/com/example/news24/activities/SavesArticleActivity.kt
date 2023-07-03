package com.example.news24.activities

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgs
import com.example.news24.R
import com.example.news24.database.MainDatabase
import com.example.news24.database.ShortenedDoc
import com.example.news24.databinding.ActivitySavesArticleBinding
import com.example.news24.fragments.viewpager2_fragments.AutomobileFragment
import com.example.news24.mvvm.NewsRepository
import com.example.news24.mvvm.NewsViewModel

class SavesArticleActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySavesArticleBinding.inflate(layoutInflater) }

    private lateinit var noteVm: NewsViewModel

//    private val args by navArgs<SavesArticleActivityArgs>()

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
//            removeSaves.setOnClickListener {
//                deleteNote()
//            }
        }
    }

    private fun shareData() {
        val link = intent.getStringExtra("Connection")
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_TEXT, link)
        intent.type = "text/plain"
        startActivity(Intent.createChooser(intent,"Choose app:"))
    }

//    private fun deleteNote() {
//        val builder = AlertDialog.Builder(this)
//        builder.setPositiveButton("Yes"){ _,_ ->
//            noteVm.deleteNote(args.dataItem)
//        }
//        builder.setNegativeButton("No"){_,_->}
//        builder.setTitle("Delete ${args.dataItem.section_name}?")
//        builder.setMessage("Are you sure that you want to delete this ${args.dataItem.section_name}?")
//        builder.create().show()
//    }
}