package com.example.musicwikimaster.utility

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.musicwikimaster.R
import java.util.*


/**
 * Utils of this application use static methods of application.
 *
 * @author Darshna/2148
 */
object AndroidAppUtils {
    private val TAG = "AndroidAppUtils"

    /**
     * check network Connection Available if not then its return
     * true/false value and show network connectivity dialog
     *
     * @param activity activity instance
     * @return true/false
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    fun isNetworkConnectionAvailable(activity: Activity?): Boolean {
        val cm = activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = Objects.requireNonNull(cm).activeNetworkInfo
        val isConnected = activeNetwork != null && activeNetwork.isConnected
      return isConnected
    }


    fun showToastMsg(activity: Activity?){
        Toast.makeText(activity,activity?.getString(R.string.no_internet),Toast.LENGTH_SHORT).show()
    }


}