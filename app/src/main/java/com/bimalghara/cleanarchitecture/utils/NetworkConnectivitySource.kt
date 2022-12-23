package com.bimalghara.cleanarchitecture.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import javax.inject.Inject

/**
 * Created by AhmedEltaher
 */

class NetworkConnectivity @Inject constructor(val context: Context) : NetworkConnectivitySource {
    override fun getNetworkInfo(): NetworkInfo? {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo
    }

    override fun isConnected(): Boolean {
        val info = getNetworkInfo()
        return info != null && info.isConnected
    }
}

interface NetworkConnectivitySource {
    fun getNetworkInfo(): NetworkInfo?
    fun isConnected(): Boolean
}