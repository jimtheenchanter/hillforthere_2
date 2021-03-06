package ie.jim.hillfort.views.map

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import ie.jim.hillfort.models.HillfortModel
import ie.jim.hillfort.views.BasePresenter
import ie.jim.hillfort.views.BaseView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class HillfortMapPresenter(view: BaseView) : BasePresenter(view) {


    fun doPopulateMap(map: GoogleMap, hillforts: List<HillfortModel>) {
        map.uiSettings.setZoomControlsEnabled(true)
        hillforts.forEach {
            val loc = LatLng(it.location.lat, it.location.lng)
            val options = MarkerOptions().title(it.title).position(loc)
            map.addMarker(options).tag = it
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 5f))
        }
    }

    fun doMarkerSelected(marker: Marker) {
        val tag = marker.tag as Long
        doAsync {
            val hillfort = marker.tag as HillfortModel
//            val hillfort = app.hillforts.findById(tag)
            uiThread {
                if (hillfort != null) view?.showHillfort(hillfort)
            }
        }
    }

    fun loadHillforts() {
        doAsync {
            val hillforts = app.hillforts.findAll()
            uiThread {
                view?.showHillforts(hillforts)
            }
        }
    }
}