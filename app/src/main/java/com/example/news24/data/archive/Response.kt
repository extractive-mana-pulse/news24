package com.example.news24.data.archive

import com.google.gson.annotations.SerializedName


data class Response (

  @SerializedName("docs" ) var docs : ArrayList<Docs> = arrayListOf(),
  @SerializedName("meta" ) var meta : Meta?           = Meta()

)