package com.example.musicwikimaster.ui.track

import androidx.lifecycle.MutableLiveData
import com.example.musicwikimaster.commonUtility.BaseViewModel
import com.example.musicwikimaster.commonUtility.disposedBy
import com.example.musicwikimaster.commonUtility.doToggleLoadingStateOf
import com.example.musicwikimaster.commonUtility.subscribeWithViewModel
import com.example.musicwikimaster.models.tags.TagInfoResponse
import com.example.musicwikimaster.models.tags.TagX
import com.example.musicwikimaster.models.track.TopTracksResponse
import com.example.musicwikimaster.models.track.Track
import com.example.musicwikimaster.usecase.artist.GetTopArtistInfoUseCase
import com.example.musicwikimaster.usecase.artist.GetTopTrackUseCase
import com.example.musicwikimaster.utility.Constants
import javax.inject.Inject

class TopTrackViewModel @Inject constructor(
  private val getTopTrackUseCase: GetTopTrackUseCase,
  private val getTopArtistInfoUseCase: GetTopArtistInfoUseCase
) : BaseViewModel() {
  val mTagName = MutableLiveData<String>()
  val mTagContent = MutableLiveData<String>()
  val mTopTrackList = MutableLiveData<List<Track>>()

  fun initialize(tagName: String) {

    mTagName.value = tagName
    val params = GetTopTrackUseCase.Params(
      Constants.DEFAULT_LASTFM_USER,
      Constants.TOP_ITEMS_LIMIT,
      Constants.API_KEY,
      tagName
    )
    val params1 = GetTopArtistInfoUseCase.Params(
      Constants.DEFAULT_LASTFM_USER,
      Constants.TOP_ITEMS_LIMIT,
      Constants.API_KEY,
      tagName
    )

    getTopTrackUseCase.build(
      params
    ).doOnNext { it -> onFetchAlbumsSuccess(it) }
        .flatMap { getTopArtistInfoUseCase.build(param = params1) }
        .doToggleLoadingStateOf(this)
        .subscribeWithViewModel(this, this::onFetchArtistInfoSuccess, this::onFetchAlbumsError)
        .disposedBy(this)
  }

  private fun getTagInfo(tagX: TagX): String {
    return tagX.wiki.content
  }

  private fun onFetchArtistInfoSuccess(response: TagInfoResponse) {
    mTagContent.value = response.tag.wiki.summary
  }

  private fun onFetchAlbumsError(t: Throwable) {
    showAlert(t) { }

  }

  private fun onFetchAlbumsSuccess(response: TopTracksResponse) {
    mTopTrackList.value = response.tracks.track
  }


}