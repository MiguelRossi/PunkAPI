package miguel.rossi.punkapi.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import miguel.rossi.punkapi.PunkAPIApp

class NetworkUtil(
    private val cm: ConnectivityManager =
        PunkAPIApp.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
) {

    fun checkNetwork(): Boolean {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            versionCodeMax28()
        } else {
            versionCodeMin23()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun versionCodeMin23(): Boolean {
        val activeNetwork = cm.getNetworkCapabilities(cm.activeNetwork)
        return when {
            activeNetwork == null -> false
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }


    @Suppress("DEPRECATION")
    private fun versionCodeMax28(): Boolean {
        val activeNetwork = cm.activeNetworkInfo
        return when {
            activeNetwork == null -> false
            activeNetwork.type == ConnectivityManager.TYPE_WIFI -> true
            activeNetwork.type == ConnectivityManager.TYPE_MOBILE -> true
            else -> false
        }
    }

}
