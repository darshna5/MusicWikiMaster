package com.example.musicwikimaster.models.artists

import com.google.gson.annotations.SerializedName

data class Image(
  @SerializedName("#text")
  val image: String,
  val size: String
)