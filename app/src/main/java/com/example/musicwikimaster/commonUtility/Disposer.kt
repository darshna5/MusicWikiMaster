package com.example.musicwikimaster.commonUtility

import io.reactivex.disposables.CompositeDisposable

interface Disposer {
  val disposeBag: CompositeDisposable
}
