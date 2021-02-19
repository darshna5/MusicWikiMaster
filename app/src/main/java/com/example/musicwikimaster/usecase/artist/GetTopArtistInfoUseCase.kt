package com.example.musicwikimaster.usecase.artist

import com.example.musicwikimaster.di.base.BaseUseCase
import com.example.musicwikimaster.models.tags.TagInfoResponse
import com.example.musicwikimaster.network.TopArtistsService
import io.reactivex.Observable
import javax.inject.Inject

class GetTopArtistInfoUseCase @Inject constructor(
  private val api: TopArtistsService
) : BaseUseCase.WithParams<GetTopArtistInfoUseCase.Params, TagInfoResponse>() {
  override fun onExecute(params: Params): Observable<TagInfoResponse> {
    return api.getTopArtistsInfo(params.user, params.limit, params.apiKey, params.tagName)
  }

  data class Params(val user: String, val limit: Int, val apiKey: String, var tagName: String)
}