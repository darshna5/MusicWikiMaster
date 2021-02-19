package com.example.musicwikimaster.ui.artist

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.musicwikimaster.R
import com.example.musicwikimaster.models.artists.Artist
import com.example.musicwikimaster.utility.ImageLoader

class TopArtistAdapter(
  private val context: Context?, private val mList: List<Artist> = emptyList()
) : RecyclerView.Adapter<TagViewHolder>() {
  lateinit var list: List<Artist>

  init {
    this.list = mList
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    return TagViewHolder(inflater, parent)
  }

  fun setTagList(mlist: List<Artist>) {
    this.list = mlist
    notifyDataSetChanged()
  }

  override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
    val artist: Artist = list[position]
    holder.bind(artist, context)
    holder.itemView.setOnClickListener {
      openUrl(artist.url)
    }
  }

  fun openUrl(url: String?) {
    if (!TextUtils.isEmpty(url)) {
      val intent = Intent()
      intent.action = Intent.ACTION_VIEW
      intent.data = Uri.parse(url)
      if (context?.packageManager?.let { intent.resolveActivity(it) } != null) {
        context.startActivity(intent)
      }
    }
  }

  override fun getItemCount(): Int = list.size

}

class TagViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
  RecyclerView.ViewHolder(inflater.inflate(R.layout.artist_item, parent, false)) {
  private var mImage: ImageView? = null
  private var mArtistName: TextView? = null

  init {
    mImage = itemView.findViewById(R.id.img_artist)
    mArtistName = itemView.findViewById((R.id.txt_artist_name))
  }

  fun bind(artist: Artist, context: Context?) {
    mArtistName?.text = artist.name
    ImageLoader.loadImage(
      context,
      artist.image[4].image,
      R.drawable.default_artist,
      mImage
    )
  }

}
