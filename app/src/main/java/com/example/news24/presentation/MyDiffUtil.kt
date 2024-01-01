package com.example.news24.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.news24.database.ShortenedDoc

class MyDiffUtil(private val notes: List<ShortenedDoc>, private val notesNew: List<ShortenedDoc>): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return notes.size
    }

    override fun getNewListSize(): Int {
        return notesNew.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return notes[oldItemPosition].id == notesNew[newItemPosition].id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when{
            notes[oldItemPosition].id != notesNew[newItemPosition].id ->{
                false
            }
            notes[oldItemPosition].title != notesNew[newItemPosition].title -> {
                false
            }
            notes[oldItemPosition].description != notesNew[newItemPosition].description ->{
                false
            }
            else -> true
        }
    }
}