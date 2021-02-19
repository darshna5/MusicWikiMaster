package com.example.musicwikimaster.models.track

import com.google.gson.annotations.SerializedName

data class Image(
  @SerializedName("#text")
  val image: String,
  val size: String
)