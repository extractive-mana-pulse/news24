package com.example.news24.data.archive
import com.google.gson.annotations.SerializedName


data class Keywords (

  @SerializedName("name"  ) var name  : String? = null,
  @SerializedName("value" ) var value : String? = null,
  @SerializedName("rank"  ) var rank  : Int?    = null,
  @SerializedName("major" ) var major : String? = null

)