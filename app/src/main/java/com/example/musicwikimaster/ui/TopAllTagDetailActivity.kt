package com.example.musicwikimaster.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.musicwikimaster.R
import com.example.musicwikimaster.utility.Constants
import kotlinx.android.synthetic.main.activity_toalltag.*

class TopAllTagDetailActivity : AppCompatActivity() {

  var mAdapter: MainPagerAdapter? = null
  var tagName = ""

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_toalltag)
    tagName = intent?.getStringExtra(Constants.TAG_NAME) ?: ""
    setToolBar()
    initializeFragments()
  }

  private fun setToolBar() {
    setSupportActionBar(toolbar);
    supportActionBar?.setDisplayHomeAsUpEnabled(true);
    supportActionBar?.setDisplayShowHomeEnabled(true);
    supportActionBar?.title = getString(R.string.tag_title);
    toolbar.setNavigationIcon(R.drawable.ic_back);
    toolbar.setNavigationOnClickListener { onBackPressed() }
  }

  private fun initializeFragments() {
    mAdapter = MainPagerAdapter(supportFragmentManager, this, tagName)
    mViewPager?.adapter = mAdapter
    mViewPager?.offscreenPageLimit = 3
    mTabLayout.setupWithViewPager(mViewPager)
  }


}