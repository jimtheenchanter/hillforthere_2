package ie.jim.hillfort.views.hillfort



import android.annotation.SuppressLint
import android.content.Intent
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import ie.jim.hillfort.helpers.checkLocationPermissions
import ie.jim.hillfort.helpers.createDefaultLocationRequest
import ie.jim.hillfort.helpers.isPermissionGranted
import org.jetbrains.anko.intentFor
import ie.jim.hillfort.helpers.showImagePicker
import ie.jim.hillfort.main.MainApp
import ie.jim.hillfort.models.Location
import ie.jim.hillfort.models.HillfortModel
import ie.jim.hillfort.views.*
import ie.jim.hillfort.views.editLocation.EditLocationView

class HillfortPresenter(view: BaseView) : BasePresenter(view) {

    var map: GoogleMap? = null
    var hillfort = HillfortModel()
    var defaultLocation = Location(52.245696, -7.139102, 15f)
    var edit = false;
    var locationService: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(view)
    val locationRequest = createDefaultLocationRequest()

    init {
        if (view.intent.hasExtra("hillfort_edit")) {
            edit = true
            hillfort = view.intent.extras?.getParcelable<HillfortModel>("hillfort_edit")!!
            view.showHillfort(hillfort)
        } else {
            if (checkLocationPermissions(view)) {
                doSetCurrentLocation()
            }
             }
    }

    @SuppressLint("MissingPermission")
    fun doSetCurrentLocation() {
        locationService.lastLocation.addOnSuccessListener {
            locationUpdate(it.latitude, it.longitude)
        }
    }


    @SuppressLint("MissingPermission")
    fun doRestartLocationUpdates() {
        var locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                if (locationResult != null && locationResult.locations != null) {
                    val l = locationResult.locations.last()
                    locationUpdate(l.latitude, l.longitude)
                }
            }
        }
        if (!edit) {
            locationService.requestLocationUpdates(locationRequest, locationCallback, null)
        }
    }
        fun doAddOrSave(title: String, description: String) {
            hillfort.title = title
            hillfort.description = description
            if (edit) {
                app.hillforts.update(hillfort)
            } else {
                app.hillforts.create(hillfort)
            }
            view?.finish()
        }

        fun doCancel() {
            view?.finish()
        }

        fun doDelete() {
            app.hillforts.delete(this.hillfort)
            view?.finish()
        }

        fun doSelectImage() {
            showImagePicker(view!!, IMAGE_REQUEST)
        }

    fun doSetLocation() {
        view?.navigateTo(VIEW.LOCATION, LOCATION_REQUEST, "location", Location(hillfort.lat, hillfort.lng, hillfort.zoom))
    }

    override fun doRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (isPermissionGranted(requestCode, grantResults)) {
            doSetCurrentLocation()
        } else {
            locationUpdate(defaultLocation.lat, defaultLocation.lng)
        }
    }
        override fun doActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
            when (requestCode) {
                IMAGE_REQUEST -> {
                    hillfort.image = data.data.toString()
                    view?.showHillfort(hillfort)
                }
                LOCATION_REQUEST -> {
                    val location = data.extras?.getParcelable<Location>("location")!!
                    hillfort.lat = location.lat
                    hillfort.lng = location.lng
                    hillfort.zoom = location.zoom
                locationUpdate(hillfort.lat, hillfort.lng)
                }
            }
        }

        fun doConfigureMap(m: GoogleMap) {
            map = m
            locationUpdate(hillfort.lat, hillfort.lng)
        }

        fun locationUpdate(lat: Double, lng: Double) {
            hillfort.lat = lat
            hillfort.lng = lng
            hillfort.zoom = 15f
            map?.clear()
            map?.uiSettings?.setZoomControlsEnabled(true)
            val options =
                MarkerOptions().title(hillfort.title).position(LatLng(hillfort.lat, hillfort.lng))
            map?.addMarker(options)
            map?.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(hillfort.lat, hillfort.lng),
                    hillfort.zoom
                )
            )
            view?.showHillfort(hillfort)
        }
    }


