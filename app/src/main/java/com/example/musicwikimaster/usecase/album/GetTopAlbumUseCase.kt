package com.example.musicwikimaster.usecase.album

import com.example.musicwikimaster.di.base.BaseUseCase
import com.example.musicwikimaster.models.album.TopAlbumsResponse
import com.example.musicwikimaster.network.TopAlbumsService
import io.reactivex.Observable
import javax.inject.Inject

class GetTopAlbumUseCase @Inject constructor(
  private val api: TopAlbumsService
) : BaseUseCase.WithParams<GetTopAlbumUseCase.Params, TopAlbumsResponse>() {
  override fun onExecute(params: Params): Observable<TopAlbumsResponse> {
    return api.getTopArtists(params.user, params.limit, params.apiKey, params.tagName)
  }

  data class Params(val user: String, val limit: Int, val apiKey: String, var tagName: String)
}