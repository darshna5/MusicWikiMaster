package com.example.musicwikimaster.ui.artist

import androidx.lifecycle.MutableLiveData
import com.example.musicwikimaster.commonUtility.BaseViewModel
import com.example.musicwikimaster.commonUtility.disposedBy
import com.example.musicwikimaster.commonUtility.doToggleLoadingStateOf
import com.example.musicwikimaster.commonUtility.subscribeWithViewModel
import com.example.musicwikimaster.models.artists.Artist
import com.example.musicwikimaster.models.artists.TopArtistResponse
import com.example.musicwikimaster.models.tags.TagInfoResponse
import com.example.musicwikimaster.models.tags.TagX
import com.example.musicwikimaster.usecase.artist.GetTopArtistInfoUseCase
import com.example.musicwikimaster.usecase.artist.GetTopArtistUseCase
import com.example.musicwikimaster.utility.Constants
import javax.inject.Inject

class TopArtistViewModel @Inject constructor(
  private val getTopArtistUseCase: GetTopArtistUseCase,
  private val getTopArtistInfoUseCase: GetTopArtistInfoUseCase

) : BaseViewModel() {
  val mTopArtistList = MutableLiveData<List<Artist>>()
  val mTagName = MutableLiveData<String>()
  val mTagContent = MutableLiveData<String>()

  fun initialize(tagName: String) {
    mTagName.value = tagName
    val params = GetTopArtistUseCase.Params(
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

    getTopArtistUseCase.build(
      params
    ).doOnNext { it -> onFetchArtistSuccess(it) }
        .flatMap { getTopArtistInfoUseCase.build(param = params1) }
        .doToggleLoadingStateOf(this)
        .subscribeWithViewModel(this, this::onFetchArtistInfoSuccess, this::onFetchAlbumsError)
        .disposedBy(this)

  }

  private fun getTagInfo(tagX: TagX): String {
    return tagX.wiki.content
  }

  private fun onFetchAlbumsError(t: Throwable) {
    showAlert(t) { }
  }

  private fun onFetchArtistInfoSuccess(response: TagInfoResponse) {
    mTagContent.value = response.tag.wiki.summary
  }

  private fun onFetchArtistSuccess(response: TopArtistResponse) {
    mTopArtistList.value = response.topartists.artist
  }


}