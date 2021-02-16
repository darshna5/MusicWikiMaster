package com.example.demoproject.service

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * Created by Darshna on 01-02-2021.
 */
interface LoginInterface {

   // var url="https://reqres.in/";

//    @POST("api/login")
//    fun loginApi(@Body loginReq: LoginReq): Observable<LoginResp>

    @Headers("User-Agent: MusicWikiMaster")
    @GET("?method=artist.getSimilar&api_key=451871b9fc8919b6f0c64fd790d9641b")
    fun getTrack():Observable<ResponseBody>


    companion object {
        fun createRetrofitInstance(): LoginInterface {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl("http://ws.audioscrobbler.com/2.0/")
                .build()

            return retrofit.create(LoginInterface::class.java)
        }
    }
}


