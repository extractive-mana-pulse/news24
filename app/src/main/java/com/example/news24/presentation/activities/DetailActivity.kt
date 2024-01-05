package com.example.news24.presentation.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.news24.R
import com.example.news24.data.room.MainDatabase
import com.example.news24.data.room.ShortenedDoc
import com.example.news24.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val title = intent.getStringExtra("title")
        val section = intent.getStringExtra("sectionData")
        val person = intent.getStringExtra("organization")
        val description = intent.getStringExtra("description")
        val link = intent.getStringExtra("link")
        val image = intent.getStringExtra("image")

        binding.apply {

            val x = Glide.with(this@DetailActivity)
                .load(image)
                .error(R.drawable.not_found)
                .centerInside()
                .into(imageArticle)

            Log.d("image", x.toString())

            setTitleTV.text = title
            sectionData.text = section
            setAuthorNameTV.text = person
            setDescTV.text = description
            webUrl.text = link
            imgSetAuthor.setImageResource(R.drawable.baseline_person_24)

            detailBackBtn.setOnClickListener {
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
                Thread {
                    db.getDao().addNews(news)
                }.start()

                add.setImageResource(R.drawable.baseline_bookmark_added_24)


//                val idToCheck = news.id
//                val exists = newsRepository.isDataExistById(idToCheck)
//
//                // Check your condition
//                if (exists) {
//                    // Change the src to a new image when the condition is met
//                    add.setImageResource(R.drawable.baseline_bookmark_added_24)
//                } else {
//                    // Set it back to the initial image when the condition is not met
//                    add.setImageResource(R.drawable.baseline_bookmark_add_24)
//                }
            }
        }
    }


    private fun shareData() {
        val link = intent.getStringExtra("link")
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_TEXT, link)
        intent.type = "text/plain"
        startActivity(Intent.createChooser(intent,"Choose app:"))
    }

//    private fun addToDatabase(title: String, person: String, description: String, section: String, link: String) {
//        val db = MainDatabase.invoke(this@DetailActivity)
//        val news = ShortenedDoc(null, title, person, description, section, link)
//        val existingItem = news.id?.let { db.getDao().getItemByIdOrTitle(it, news.title) }
//        if (existingItem != null) {
//            binding.add.setImageResource(R.drawable.baseline_bookmark_added_24)
//            isImage1Displayed = false
//            Toast.makeText(this@DetailActivity, "Item already exists", Toast.LENGTH_SHORT).show()
//        } else {
//            Toast.makeText(this@DetailActivity, "Item added to database", Toast.LENGTH_SHORT).show()
//        }
//    }
}