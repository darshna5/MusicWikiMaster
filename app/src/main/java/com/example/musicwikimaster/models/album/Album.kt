package com.example.musicwikimaster.models.album

data class Album(
  val artist: Artist,
  val image: List<Image>,
  val mbid: String,
  val name: String,
  val url: String
)