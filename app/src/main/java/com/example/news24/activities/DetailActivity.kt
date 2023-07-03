package com.example.news24.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.news24.R
import com.example.news24.constants.Constants.Companion.TAG
import com.example.news24.data.Multimedia
import com.example.news24.database.MainDatabase
import com.example.news24.database.ShortenedDoc
import com.example.news24.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }

    private var isImage1Displayed = true

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val title = intent.getStringExtra("title")
        val section = intent.getStringExtra("sectionData")
        val person = intent.getStringExtra("organization")
        val description = intent.getStringExtra("description")
        val link = intent.getStringExtra("link")

        val multimedia = mutableListOf<Multimedia>()

        Log.d(TAG, multimedia.lastOrNull()?.url.toString())

        val url = multimedia.lastOrNull()?.url

        binding.apply {
            if (url == null){
                imgSet.setImageResource(R.drawable.nyt)
            }else{
                Glide.with(root).load(url).into(imgSet)
            }
            setTitleTV.text = title
            sectionData.text = section
            setAuthorNameTV.text = person
            setDescTV.text = description
            webUrl.text = link
            imgSetAuthor.setImageResource(R.drawable.baseline_person_24)

            back.setOnClickListener {
                onBackPressed()
            }

            sharing.setOnClickListener {
                shareData()
            }

            add.setOnClickListener {
                val db = MainDatabase.invoke(this@DetailActivity)
                val news = ShortenedDoc(
                    null,
                    title.toString(),
                    person.toString(),
                    description.toString(),
                    section.toString(),
                    link.toString()
                )
                isImage1Displayed = if (isImage1Displayed) {
                    Thread {
                        db.getDao().addNews(news)
                    }.start()
                    add.setImageResource(R.drawable.baseline_bookmark_added_24)
                    false
                } else {
                    add.setImageResource(R.drawable.baseline_bookmark_add_24)
                    true
                }
            }
        }
        // check if added to database
        // addToDatabase(title.toString(),person.toString(),description.toString(),section.toString(),link.toString())
    }

    private fun shareData() {
        val link = intent.getStringExtra("link")
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_TEXT, link)
        intent.type = "text/plain"
        startActivity(Intent.createChooser(intent,"Choose app:"))
    }

    private fun addToDatabase(title: String, person: String, description: String, section: String, link: String) {
        val db = MainDatabase.invoke(this@DetailActivity)
        val news = ShortenedDoc(null, title, person, description, section, link)
        val existingItem = news.id?.let { db.getDao().getItemByIdOrTitle(it, news.title) }
        if (existingItem != null) {
            binding.add.setImageResource(R.drawable.baseline_bookmark_added_24)
            isImage1Displayed = false
            Toast.makeText(this@DetailActivity, "Item already exists", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this@DetailActivity, "Item added to database", Toast.LENGTH_SHORT).show()
        }
    }
}