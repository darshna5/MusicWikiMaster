package com.example.musicwikimaster.ui.tags

import androidx.lifecycle.MutableLiveData
import com.example.musicwikimaster.commonUtility.BaseViewModel
import com.example.musicwikimaster.commonUtility.disposedBy
import com.example.musicwikimaster.commonUtility.doToggleLoadingStateOf
import com.example.musicwikimaster.commonUtility.subscribeWithViewModel
import com.example.musicwikimaster.models.tags.Tag
import com.example.musicwikimaster.models.tags.TopTagResponse
import com.example.musicwikimaster.usecase.tags.GetAllTagsUseCase
import com.example.musicwikimaster.utility.Constants
import javax.inject.Inject

class TopAllTagsViewModel @Inject constructor(
  private val getAllTagsUseCase: GetAllTagsUseCase
) : BaseViewModel() {
  val mTagList = MutableLiveData<List<Tag>>()

  fun initialize() {
    hitGetAllTag()
  }

  private fun hitGetAllTag() {

    getAllTagsUseCase.build(
      GetAllTagsUseCase.Params(
        Constants.DEFAULT_LASTFM_USER,
        Constants.API_KEY
      )
    )
        .doToggleLoadingStateOf(this)
        .subscribeWithViewModel(this, this::onFetchAllSuccess, this::onFetchAlbumsError)
        .disposedBy(this)
  }

  private fun onFetchAllSuccess(response: TopTagResponse) {
    print("darshna size=${response.toptags.tag.size}")
    mTagList.value = response.toptags.tag
  }

  private fun onFetchAlbumsError(t: Throwable) {
    showAlert(t) { }
  }

}