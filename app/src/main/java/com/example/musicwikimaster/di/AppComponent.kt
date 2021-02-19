package com.example.musicwikimaster.di

import android.content.Context
import com.example.musicwikimaster.ui.MainActivity
import com.example.musicwikimaster.ui.album.TopAlbumsFragment
import com.example.musicwikimaster.ui.artist.TopArtistsFragment
import com.example.musicwikimaster.ui.tags.TopAllTagsFragment
import com.example.musicwikimaster.ui.track.TopTrackFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

// Definition of a Dagger component that adds info from the StorageModule to the graph
@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {

  // Factory to create instances of the AppComponent
  @Component.Factory
  interface Factory {
    // With @BindsInstance, the Context passed in will be available in the graph
    fun create(@BindsInstance context: Context): AppComponent
  }

  fun inject(activity: MainActivity)
  fun inject(topAllTagsFragment: TopAllTagsFragment)
  fun inject(topArtistsFragment: TopArtistsFragment)
  fun inject(topAlbumsFragment: TopAlbumsFragment)
  fun inject(topTrackFragment: TopTrackFragment)
}