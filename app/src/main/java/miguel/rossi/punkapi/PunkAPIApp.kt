package miguel.rossi.punkapi

import android.app.Application
import timber.log.Timber
import timber.log.Timber.DebugTree

class PunkAPIApp : Application() {

    companion object {
        lateinit var instance: PunkAPIApp
            private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        plantTree()

    }

    private fun plantTree() {
        if (BuildConfig.DEBUG)
            Timber.plant(DebugTree())
    }

}
