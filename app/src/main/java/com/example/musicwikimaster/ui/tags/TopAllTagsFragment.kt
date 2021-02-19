package com.example.musicwikimaster.ui.tags

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.musicwikimaster.databinding.FragmentAllTopTagsBinding
import com.example.musicwikimaster.di.MyApplication
import com.example.musicwikimaster.utility.AndroidAppUtils
import kotlinx.android.synthetic.main.fragment_all_top_tags.*
import kotlinx.android.synthetic.main.fragment_artists.*
import javax.inject.Inject

class TopAllTagsFragment : Fragment() {
  // @Inject annotated fields will be provided by Dagger
  @Inject lateinit var vm: TopAllTagsViewModel
  var isExpand: Boolean = false
  private val tagAdapter by lazy {
    TopAllTagAdapter(activity)
  }

  private val tagAdapterExtand by lazy {
    TopAllTagAdapter(activity)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View? {
    val binding: FragmentAllTopTagsBinding =
        FragmentAllTopTagsBinding.inflate(inflater, container, false)
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
    if (AndroidAppUtils.isNetworkConnectionAvailable(activity))
      initializeViews()
    else
      AndroidAppUtils.showToastMsg(activity)
  }

  private fun initializeViews() {
    ll_tagsRecyclerView.visibility = View.VISIBLE
    ll_tagsRecyclerViewExpand.visibility = View.GONE
    vm.initialize()
    ll_tag_expand.setOnClickListener {
      if (isExpand) {
        ll_tagsRecyclerView.visibility = View.GONE
        ll_tagsRecyclerViewExpand.visibility = View.VISIBLE
        isExpand = false
      } else {
        ll_tagsRecyclerView.visibility = View.VISIBLE
        ll_tagsRecyclerViewExpand.visibility = View.GONE

        isExpand = true
      }
    }
    prgrs_main_all.visibility = View.VISIBLE

    vm.mTagList.observe(viewLifecycleOwner, Observer {
      tagAdapter.setTagList(it.take(10))
      prgrs_main_all.visibility = View.GONE
      if (it.isEmpty()) {
        empty_layout_all.visibility = View.VISIBLE
        return@Observer
      }
      tagsRecyclerView.apply {
        layoutManager = GridLayoutManager(
          context,
          3
        )
        adapter = tagAdapter
      }
      tagAdapterExtand.setTagList(it)
      tagsRecyclerViewExpand.apply {
        layoutManager = GridLayoutManager(
          context,
          3
        )
        adapter = tagAdapterExtand
      }
    })
  }

  companion object {
    @JvmStatic
    fun createNewInstance(): TopAllTagsFragment {
      return TopAllTagsFragment()
    }
  }
}


