package com.example.musicwikimaster.ui.artist;

import com.example.musicwikimaster.models.artists.Artist;

import java.util.List;


public interface TopArtistsView {
  void showProgress();

  void hideProgress();

  void updateData(List<Artist> topArtists);

  void showError();

  void showEmpty();

  void hidEmpty();

}
