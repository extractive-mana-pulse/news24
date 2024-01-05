package com.example.news24.presentation.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.news24.presentation.MyDiffUtil
import com.example.news24.R
import com.example.news24.presentation.activities.SavesArticleActivity
import com.example.news24.data.room.MainDatabase
import com.example.news24.data.room.ShortenedDoc
import com.example.news24.databinding.SavedNewsUiBinding

class SavedNewsAdapter: RecyclerView.Adapter<SavedNewsAdapter.SavedViewHolder>() {

    private var listSaves = emptyList<ShortenedDoc>()

    inner class SavedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = SavedNewsUiBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.saved_news_ui, parent, false)
        return SavedViewHolder(view)
    }

    @SuppressLint("DiscouragedPrivateApi")
    override fun onBindViewHolder(holder: SavedViewHolder, position: Int) {
        val data = listSaves[position]
        holder.binding.titleSavesTv.text = data.title
        holder.binding.personTv.text = data.person
        holder.binding.sectionTv.text = data.section_name

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, SavesArticleActivity::class.java)
            intent.putExtra("Entitlement", data.title)
            intent.putExtra("sectionInfo", data.section_name)
            intent.putExtra("byPerson", data.person)
            intent.putExtra("Explanation", data.description)
            intent.putExtra("Connection", data.link)
            holder.itemView.context.startActivity(intent)
        }
        holder.binding.removeFromSaves.setOnClickListener {
            val db = MainDatabase.invoke(holder.itemView.context)
            db.getDao().delete(data)
        }
    }

    override fun getItemCount(): Int {
        return listSaves.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: List<ShortenedDoc>){
        val diffUtil = MyDiffUtil(listSaves, newData)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        listSaves = newData as MutableList<ShortenedDoc>
        diffResult.dispatchUpdatesTo(this)
        notifyDataSetChanged()
    }
}