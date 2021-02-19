package com.example.musicwikimaster.ui.tags

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.musicwikimaster.R
import com.example.musicwikimaster.models.tags.Tag
import com.example.musicwikimaster.ui.TopAllTagDetailActivity
import com.example.musicwikimaster.utility.Constants

class TopAllTagAdapter(private val context: Context?) : RecyclerView.Adapter<TagViewHolder>() {
  lateinit var list: List<Tag>
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    return TagViewHolder(inflater, parent)
  }

  fun setTagList(list: List<Tag>) {
    this.list = list
  }

  override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
    val tag: Tag = list[position]
    holder.bind(tag)
    holder.itemView.setOnClickListener {
      val intent = Intent(context, TopAllTagDetailActivity::class.java)
      intent.putExtra(Constants.TAG_NAME, tag.name)
      context?.startActivity(intent)
    }
  }

  override fun getItemCount(): Int = list.size

}

class TagViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
  RecyclerView.ViewHolder(inflater.inflate(R.layout.item_top_tag_list, parent, false)) {
  private var mTagButton: Button? = null

  init {
    mTagButton = itemView.findViewById(R.id.button)
  }

  fun bind(tag: Tag) {
    mTagButton?.text = tag.name
  }

}
