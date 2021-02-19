package com.example.musicwikimaster.network

import com.example.musicwikimaster.models.tags.TopTagResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface AllTagService {
  @GET("?method=tag.getTopTags&format=json")
  fun getAllTags(
    @Query("user") user: String?,
    @Query("api_key") apiKey: String?
  ): Observable<TopTagResponse>
}