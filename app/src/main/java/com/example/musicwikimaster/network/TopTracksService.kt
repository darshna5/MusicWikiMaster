package com.example.musicwikimaster.network

import com.example.musicwikimaster.models.track.TopTracksResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface TopTracksService {
  @GET("?method=tag.gettoptracks&format=json")
  fun getTopTracks(
    @Query("user") user: String?, @Query("limit") limit: Int,
    @Query("api_key") apiKey: String?, @Query("tag") tag: String?
  ): Observable<TopTracksResponse>
}