package com.example.musicwikimaster.usecase.artist

import com.example.musicwikimaster.di.base.BaseUseCase
import com.example.musicwikimaster.models.track.TopTracksResponse
import com.example.musicwikimaster.network.TopTracksService
import io.reactivex.Observable
import javax.inject.Inject

class GetTopTrackUseCase @Inject constructor(
  private val api: TopTracksService
) : BaseUseCase.WithParams<GetTopTrackUseCase.Params, TopTracksResponse>() {
  override fun onExecute(params: Params): Observable<TopTracksResponse> {
    return api.getTopTracks(params.user, params.limit, params.apiKey, params.tagName)
  }

  data class Params(val user: String, val limit: Int, val apiKey: String, var tagName: String)
}