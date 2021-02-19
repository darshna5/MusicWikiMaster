package com.example.musicwikimaster.network

import com.example.musicwikimaster.models.artists.TopArtistResponse
import com.example.musicwikimaster.models.tags.TagInfoResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface TopArtistsService {
  @GET("?method=tag.gettopartists&format=json")
  fun getTopArtists(
    @Query("user") user: String?, @Query("limit") limit: Int,
    @Query("api_key") apiKey: String?, @Query("tag") tag: String?
  ): Observable<TopArtistResponse>

  @GET("?method=tag.getinfo&format=json")
  fun getTopArtistsInfo(
    @Query("user") user: String?, @Query("limit") limit: Int,
    @Query("api_key") apiKey: String?, @Query("tag") tag: String?
  ): Observable<TagInfoResponse>
}