package com.example.musicwikimaster.network

import com.example.musicwikimaster.models.album.TopAlbumsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Bassem Samy on 6/16/2017.
 */
interface TopAlbumsService {
  @GET("?method=tag.gettopalbums&format=json")
  fun getTopArtists(
    @Query("user") user: String?, @Query("limit") limit: Int,
    @Query("api_key") apiKey: String?, @Query("tag") tag: String?
  ): Observable<TopAlbumsResponse>
}