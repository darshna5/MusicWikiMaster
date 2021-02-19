package com.example.musicwikimaster.models.album

import com.google.gson.annotations.SerializedName

data class Image(
  @SerializedName("#text")
  val image: String,
  val size: String
)