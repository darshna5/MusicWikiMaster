package com.example.musicwikimaster.usecase.tags

import com.example.musicwikimaster.di.base.BaseUseCase
import com.example.musicwikimaster.models.tags.TopTagResponse
import com.example.musicwikimaster.network.AllTagService
import io.reactivex.Observable
import javax.inject.Inject

class GetAllTagsUseCase @Inject constructor(
  private val api: AllTagService
) : BaseUseCase.WithParams<GetAllTagsUseCase.Params, TopTagResponse>() {
  override fun onExecute(params: Params): Observable<TopTagResponse> {
    return api.getAllTags(params.user, params.apiKey)
  }

  data class Params(val user: String, val apiKey: String)
}