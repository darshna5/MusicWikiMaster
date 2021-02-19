package com.example.musicwikimaster.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.musicwikimaster.R
import com.example.musicwikimaster.ui.tags.TopAllTagsFragment

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {

    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val fragmentManager: FragmentManager = supportFragmentManager
    val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
    fragmentTransaction.replace(R.id.ll_tag_container, TopAllTagsFragment.createNewInstance())
        .commit()
  }

  override fun onResume() {
    super.onResume()

  }
}