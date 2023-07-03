package com.example.news24.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news24.R
import com.example.news24.activities.DetailActivity
import com.example.news24.data.Doc
import com.example.news24.data.Multimedia
import com.example.news24.database.MainDatabase
import com.example.news24.database.ShortenedDoc
import com.example.news24.databinding.RcViewUiBinding

class MyAdapter: RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    private var list = emptyList<Doc>()

    inner class MyViewHolder(item: View): RecyclerView.ViewHolder(item){
        val binding = RcViewUiBinding.bind(item)
    }

    private val differCallback = object : DiffUtil.ItemCallback<Doc>(){
        override fun areItemsTheSame(oldItem: Doc, newItem: Doc): Boolean {
            return oldItem.uri == newItem.uri
        }

        override fun areContentsTheSame(oldItem: Doc, newItem: Doc): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rc_view_ui, parent, false)
        return MyViewHolder(view)
    }

    @SuppressLint("DiscouragedPrivateApi")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = list[position]
        val multimedia = mutableListOf<Multimedia>()
        val url = multimedia.lastOrNull()?.url
        holder.itemView.apply {
            if (url == null){
                holder.binding.img.setImageResource(R.drawable.nyt)
            }else{
                Glide.with(this).load(url).into(holder.binding.img)
            }
            holder.binding.Title.text = data.abstract
            holder.binding.Person.text = data.byline.original
            holder.binding.sectionName.text = data.section_name
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("title", data.abstract)
            intent.putExtra("sectionData", data.section_name)
            intent.putExtra("organization", data.byline.original)
            intent.putExtra("description", data.lead_paragraph)
            intent.putExtra("link", data.web_url)
            holder.itemView.context.startActivity(intent)
        }

        holder.binding.optionsMenu.setOnClickListener {

            val popupMenu = PopupMenu(holder.itemView.context, it)

            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId){
                    R.id.share -> {
                        val intent = Intent(Intent.ACTION_SEND)
                        intent.putExtra(Intent.EXTRA_TEXT, data.web_url)
                        intent.type = "text/plain"
                        holder.itemView.context.startActivity(Intent.createChooser(intent,"Choose app:"))
                        true
                    }
                    R.id.save -> {
                        val db = MainDatabase.invoke(holder.itemView.context)
                        val news = ShortenedDoc(
                            null,
                            data.abstract,
                            data.byline.original,
                            data.lead_paragraph,
                            data.section_name,
                            data.web_url
                        )
                        db.getDao().addNews(news)
                        true
                    }
                    R.id.delete -> {

                        true
                    }
                    else -> false
                }
            }

            popupMenu.inflate(R.menu.popup_menu)

            try {
                val fieldMPopup = PopupMenu::class.java.getDeclaredField("mPopup")
                fieldMPopup.isAccessible = true
                val mPopup = fieldMPopup.get(popupMenu)
                mPopup.javaClass
                    .getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                    .invoke(mPopup, true)
            } catch (e: Exception){
                Log.e("Main", "Error showing menu icons.", e)
            } finally {
                popupMenu.show()
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: List<Doc>) {
        list = newList
        notifyDataSetChanged()
    }
}