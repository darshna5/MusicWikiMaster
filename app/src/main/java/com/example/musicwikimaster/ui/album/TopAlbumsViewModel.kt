package com.example.musicwikimaster.ui.album

import androidx.lifecycle.MutableLiveData
import com.example.musicwikimaster.commonUtility.BaseViewModel
import com.example.musicwikimaster.commonUtility.disposedBy
import com.example.musicwikimaster.commonUtility.doToggleLoadingStateOf
import com.example.musicwikimaster.commonUtility.subscribeWithViewModel
import com.example.musicwikimaster.models.album.Album
import com.example.musicwikimaster.models.album.TopAlbumsResponse
import com.example.musicwikimaster.models.tags.TagInfoResponse
import com.example.musicwikimaster.models.tags.TagX
import com.example.musicwikimaster.usecase.album.GetTopAlbumUseCase
import com.example.musicwikimaster.usecase.artist.GetTopArtistInfoUseCase
import com.example.musicwikimaster.utility.Constants
import javax.inject.Inject

class TopAlbumsViewModel @Inject constructor(
  private val getTopAlbumUseCase: GetTopAlbumUseCase,
  private val getTopArtistInfoUseCase: GetTopArtistInfoUseCase
) : BaseViewModel() {
  val mTagName = MutableLiveData<String>()
  val mTagContent = MutableLiveData<String>()
  val mTopAlbumList = MutableLiveData<List<Album>>()
  val isDataAvailable = MutableLiveData<Boolean>(false)

  fun initialize(tagName: String) {

    mTagName.value = tagName
    val params = GetTopAlbumUseCase.Params(
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

    getTopAlbumUseCase.build(
      params
    ).doOnNext { it -> onFetchAlbumsSuccess(it) }
        .flatMap { getTopArtistInfoUseCase.build(param = params1) }
        .doToggleLoadingStateOf(this)
        .subscribeWithViewModel(this, this::onFetchArtistInfoSuccess, this::onFetchAlbumsError)
        .disposedBy(this)
  }


  private fun onFetchArtistInfoSuccess(response: TagInfoResponse) {
    mTagContent.value = response.tag.wiki.summary
  }

  private fun onFetchAlbumsError(t: Throwable) {
    showAlert(t) { }
  }

  private fun onFetchAlbumsSuccess(response: TopAlbumsResponse) {
    mTopAlbumList.value = response.albums.album
    isDataAvailable.value=true

  }


}