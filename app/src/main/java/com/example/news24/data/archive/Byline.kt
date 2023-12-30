package com.example.news24.data.archive
import com.google.gson.annotations.SerializedName


data class Byline (

  @SerializedName("original"     ) var original     : String?           = null,
  @SerializedName("person"       ) var person       : ArrayList<Person> = arrayListOf(),
  @SerializedName("organization" ) var organization : String?           = null

)