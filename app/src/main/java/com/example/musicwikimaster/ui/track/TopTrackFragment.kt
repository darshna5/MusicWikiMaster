package com.example.musicwikimaster.ui.track

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicwikimaster.databinding.FragmentTracksBinding
import com.example.musicwikimaster.di.MyApplication
import com.example.musicwikimaster.utility.Constants
import kotlinx.android.synthetic.main.fragment_artists.*
import kotlinx.android.synthetic.main.fragment_tracks.*
import kotlinx.android.synthetic.main.fragment_tracks.empty_layout
import kotlinx.android.synthetic.main.fragment_tracks.prgrs_main
import javax.inject.Inject

class TopTrackFragment : Fragment() {
  // @Inject annotated fields will be provided by Dagger
  @Inject lateinit var vm: TopTrackViewModel
  var tagName = ""

  private val topTrackAdapter by lazy {
    TopTrackAdapter(context)
  }

  private fun loadArguments() {
    arguments?.apply {
      tagName = getString(Constants.TAG_NAME) ?: throw IllegalStateException()
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View? {
    val binding: FragmentTracksBinding = FragmentTracksBinding.inflate(inflater, container, false)
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
    vm.initialize(tagName)
    prgrs_main.visibility = View.VISIBLE
    vm.mTopTrackList.observe(viewLifecycleOwner, Observer {
      prgrs_main.visibility = View.GONE
      if (it.isEmpty()) {
        empty_layout.visibility = View.VISIBLE
        return@Observer
      }
      topTrackAdapter.setTagList(it)
      top_track_RecyclerView.apply {
        layoutManager = LinearLayoutManager(
          context,
          LinearLayoutManager.VERTICAL,
          false
        )
        adapter = topTrackAdapter
      }

    })
    vm.mTagContent.observe(viewLifecycleOwner, Observer {
      tagInfoTrack.text = vm.mTagContent.value
    })
  }

  companion object {
    @JvmStatic
    fun newInstance(tagName: String): TopTrackFragment {
      val bundle = Bundle().apply {
        putString(Constants.TAG_NAME, tagName)
      }
      return TopTrackFragment().also { it.arguments = bundle }
    }
  }
}