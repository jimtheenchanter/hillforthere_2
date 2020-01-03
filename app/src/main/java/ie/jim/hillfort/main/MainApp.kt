// A single instance of this class will be created when our application will be launched.
// A reference to this application can be acquired in other activities as needed.
package ie.jim.hillfort.main

import android.app.Application

import ie.jim.hillfort.models.HillfortStore
import ie.jim.hillfort.models.firebase.HillfortFireStore
import org.jetbrains.anko.AnkoLogger

class MainApp : Application(), AnkoLogger {


    lateinit var hillforts: HillfortStore

    override fun onCreate() {
      super.onCreate()
        hillforts = HillfortFireStore(applicationContext)



    }
}

