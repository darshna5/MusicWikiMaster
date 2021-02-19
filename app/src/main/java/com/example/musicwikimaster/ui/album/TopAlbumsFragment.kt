package com.example.musicwikimaster.ui.album

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicwikimaster.databinding.FragmentAalbumsBinding
import com.example.musicwikimaster.di.MyApplication
import com.example.musicwikimaster.utility.Constants
import kotlinx.android.synthetic.main.fragment_aalbums.*
import javax.inject.Inject

class TopAlbumsFragment : Fragment() {
  // @Inject annotated fields will be provided by Dagger
  @Inject lateinit var vm: TopAlbumsViewModel
  var tagName = ""

  private val topAlbumAdapter by lazy {
    TopAlbumAdapter(context)
  }

  private fun loadArguments() {
    arguments?.apply {
      tagName = getString(Constants.TAG_NAME) ?: throw IllegalStateException()
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View? {
    val binding: FragmentAalbumsBinding = FragmentAalbumsBinding.inflate(inflater, container, false)
    val view = binding.root
    binding.vm = this.vm
    return view
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    // Ask Dagger to inject our dependencies
    (context.applicationContext as MyApplication).appComponent.inject(this)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    loadArguments()
    prgrs_main.visibility = View.VISIBLE
    vm.initialize(tagName)
    vm.mTopAlbumList.observe(viewLifecycleOwner, Observer {
      prgrs_main.visibility = View.GONE
      if (it.isEmpty()) {
        empty_layout.visibility = View.VISIBLE
        return@Observer
      }


      topAlbumAdapter.setTagList(it)
      top_albums_RecyclerView.apply {
        layoutManager = LinearLayoutManager(
          context,
          LinearLayoutManager.VERTICAL,
          false
        )
        adapter = topAlbumAdapter
      }

    })
    vm.mTagContent.observe(viewLifecycleOwner, Observer {
      tagInfoAlbum.text = vm.mTagContent.value
    })
  }

  companion object {
    @JvmStatic
    fun newInstance(tagName: String): TopAlbumsFragment {
      val bundle = Bundle().apply {
        putString(Constants.TAG_NAME, tagName)
      }
      return TopAlbumsFragment().also { it.arguments = bundle }
    }
  }
}