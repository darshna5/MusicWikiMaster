package com.example.musicwikimaster.ui

import android.content.Context
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.musicwikimaster.R
import com.example.musicwikimaster.ui.album.TopAlbumsFragment
import com.example.musicwikimaster.ui.artist.TopArtistsFragment
import com.example.musicwikimaster.ui.track.TopTrackFragment
import java.lang.ref.WeakReference
import java.util.*

class MainPagerAdapter(
  fm: FragmentManager?, context: Context, tagName: String
) : FragmentPagerAdapter(fm!!) {
  private val topArtistsTitle: String
  private val topTracksTitle: String
  private val topAlbumsTitle: String
  private val fragments: Hashtable<Int, WeakReference<Fragment>>
  private var mTagName: String = ""

  init {
    this.mTagName = tagName
  }

  override fun getItem(position: Int): Fragment {
    when (position) {
      TOP_ARTISTS_INDEX -> {
        val fr: Fragment = TopArtistsFragment.newInstance(mTagName)
        fragments[position] = WeakReference(fr)
        return fr
      }
      TOP_ALBUMS_INDEX -> {
        val fr: Fragment = TopAlbumsFragment.newInstance(mTagName)
        fragments[position] = WeakReference(fr)
        return fr
      }
      TOP_TRACKS_INDEX -> {
        val fr: Fragment = TopTrackFragment.newInstance(mTagName)
        fragments[position] = WeakReference(fr)
        return fr
      }
    }
    return TopArtistsFragment.newInstance(mTagName)
  }

  override fun getPageTitle(position: Int): CharSequence? {
    when (position) {
      TOP_ARTISTS_INDEX -> {
        return topArtistsTitle
      }
      TOP_ALBUMS_INDEX -> {
        return topAlbumsTitle
      }
      TOP_TRACKS_INDEX -> {
        return topTracksTitle
      }
    }
    return super.getPageTitle(position)
  }

  override fun getCount(): Int {
    return NUMBER_OF_ITEMS
  }

  override fun destroyItem(
    container: ViewGroup, position: Int, `object`: Any
  ) {
    super.destroyItem(container, position, `object`)
    fragments.remove(position)
  }

  fun getFragments(): ArrayList<Fragment?> {
    val list =
        ArrayList<Fragment?>()
    for (i in 0 until fragments.size) {
      list.add(fragments[i]?.get())
    }
    return list
  }

  companion object {
    private const val NUMBER_OF_ITEMS = 3
    private const val TOP_ARTISTS_INDEX = 0
    private const val TOP_ALBUMS_INDEX = 1
    private const val TOP_TRACKS_INDEX = 2
  }

  init {
    topArtistsTitle = context.getString(R.string.top_artists_title)
    topTracksTitle = context.getString(R.string.top_tracks_title)
    topAlbumsTitle = context.getString(R.string.top_albums_title)
    fragments =
        Hashtable()
  }
}