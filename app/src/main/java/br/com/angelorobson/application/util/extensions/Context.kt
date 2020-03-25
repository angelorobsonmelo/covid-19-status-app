package br.com.angelorobson.application.util.extensions

import android.content.Context
import android.net.ConnectivityManager

val Context.isConnected: Boolean
    get() {
        return (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
            .activeNetworkInfo?.isConnected == true
    }