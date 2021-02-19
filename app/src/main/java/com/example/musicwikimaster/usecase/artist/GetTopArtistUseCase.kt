package com.example.musicwikimaster.usecase.artist

import com.example.musicwikimaster.di.base.BaseUseCase
import com.example.musicwikimaster.models.artists.TopArtistResponse
import com.example.musicwikimaster.network.TopArtistsService
import io.reactivex.Observable
import javax.inject.Inject

class GetTopArtistUseCase @Inject constructor(
  private val api: TopArtistsService
) : BaseUseCase.WithParams<GetTopArtistUseCase.Params, TopArtistResponse>() {
  override fun onExecute(params: Params): Observable<TopArtistResponse> {
    return api.getTopArtists(params.user, params.limit, params.apiKey, params.tagName)
  }

  data class Params(val user: String, val limit: Int, val apiKey: String, var tagName: String)
}