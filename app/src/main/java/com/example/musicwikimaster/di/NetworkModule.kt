package com.example.musicwikimaster.di

import com.example.musicwikimaster.network.AllTagService
import com.example.musicwikimaster.network.TopAlbumsService
import com.example.musicwikimaster.network.TopArtistsService
import com.example.musicwikimaster.network.TopTracksService
import com.example.musicwikimaster.utility.Constants
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {

  @Provides
  @Singleton
  internal fun provideRetrofit(): Retrofit =
      Retrofit.Builder()
          .baseUrl(Constants.BASE_URL)
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .addConverterFactory(GsonConverterFactory.create())
          .build()

  @Reusable
  @Provides
  @JvmStatic
  internal fun provideAllTagService(retrofit: Retrofit): AllTagService =
      retrofit.create(AllTagService::class.java)

  @Reusable
  @Provides
  @JvmStatic
  internal fun provideTopAlbumsService(retrofit: Retrofit): TopAlbumsService =
      retrofit.create(TopAlbumsService::class.java)

  @Reusable
  @Provides
  @JvmStatic
  internal fun provideTopArtistsService(retrofit: Retrofit): TopArtistsService =
      retrofit.create(TopArtistsService::class.java)

  @Reusable
  @Provides
  @JvmStatic
  internal fun provideTopTracksService(retrofit: Retrofit): TopTracksService =
      retrofit.create(TopTracksService::class.java)

}