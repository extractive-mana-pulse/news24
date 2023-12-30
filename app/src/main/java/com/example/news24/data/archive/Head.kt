package com.example.news24.data.archive

import com.google.gson.annotations.SerializedName


data class Head (

  @SerializedName("copyright" ) var copyright : String?   = null,
  @SerializedName("response"  ) var response  : Response? = Response()

)