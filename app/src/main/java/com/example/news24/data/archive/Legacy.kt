package com.example.news24.data.archive
import com.google.gson.annotations.SerializedName


data class Legacy (

  @SerializedName("xlarge"       ) var xlarge       : String? = null,
  @SerializedName("xlargewidth"  ) var xlargewidth  : Int?    = null,
  @SerializedName("xlargeheight" ) var xlargeheight : Int?    = null

)