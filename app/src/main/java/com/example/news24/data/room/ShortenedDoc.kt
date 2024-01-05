package com.example.news24.data.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "News")
data class ShortenedDoc(

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @ColumnInfo(name = "Title")
    @SerializedName("`abstract`")
    val title: String,

    @ColumnInfo(name = "organization")
    @SerializedName("byline")
    val person: String,

    @ColumnInfo(name = "Description")
    @SerializedName("lead_paragraph")
    val description: String,

    @ColumnInfo(name = "section name")
    @SerializedName("section_name")
    val section_name: String,

    @ColumnInfo(name = "Link")
    @SerializedName("web_url")
    val link: String
) : Parcelable
