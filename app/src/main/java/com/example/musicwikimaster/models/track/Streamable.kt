package com.example.musicwikimaster.models.track

import com.google.gson.annotations.SerializedName

data class Streamable(
  @SerializedName("#text")
  val image: String,
  val fulltrack: String
)