package com.example.musicwikimaster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.demoproject.service.LoginInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickLogin(view: View) {

        val loginInterface by lazy {
            LoginInterface.createRetrofitInstance()
        }
        loginInterface.getTrack()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> showResult(result) },
                { error -> showError(error.message) }
            )

    }


    fun showResult(loginResp: ResponseBody) {
        println("api response=" + loginResp.toString())
    }

    fun showError(error: String?) {
        println("api response error=" + error)

    }

}
