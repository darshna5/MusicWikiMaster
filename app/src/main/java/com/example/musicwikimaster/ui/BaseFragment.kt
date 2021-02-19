package com.example.musicwikimaster.ui

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
  protected abstract fun searchUserName(userName: String?)
}