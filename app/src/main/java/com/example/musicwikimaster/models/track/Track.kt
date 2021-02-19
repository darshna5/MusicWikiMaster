package com.example.musicwikimaster.models.track

data class Track(
  val artist: Artist,
  val duration: String,
  val image: List<Image>,
  val mbid: String,
  val name: String,
  val streamable: Streamable,
  val url: String
)