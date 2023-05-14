package com.devst.a13room

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class NetworkUtil {
    companion object{
        fun isInternet(context: Context):Boolean{
            (context.getSystemService(Context.CONNECTIVITY_SERVICE)as ConnectivityManager).run {
                 if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){

                     return this.getNetworkCapabilities(this.activeNetwork)?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)?:false
                }
                else{
                    return this.activeNetworkInfo?.isConnected?:false
                 }
            }
        }


    }

}