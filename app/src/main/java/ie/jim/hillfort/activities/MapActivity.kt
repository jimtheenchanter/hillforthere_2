package ie.jim.hillfort.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ie.jim.hillfort.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import ie.jim.hillfort.models.HillfortJSONStore
import ie.jim.hillfort.models.HillfortModel
import ie.jim.hillfort.models.Location
import kotlinx.android.synthetic.main.activity_hillfort.*

class MapActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerDragListener, GoogleMap.OnMarkerClickListener  {

    private lateinit var map: GoogleMap
    var location = Location()
//    var hillfort = HillfortModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map) //display  the map layout
        location = intent.extras?.getParcelable<Location>("location")!! //set  location  from model

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }



    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val loc = LatLng(location.lat, location.lng)
        val options = MarkerOptions()
//            .title(hillfort.title +"Here here! hold marker to update" )
            .snippet("GPS : " + loc.toString())
            .title("Hillfort here! hold marker to update" )
            .draggable(true)
            .position(loc)
        map.addMarker(options)
        map.setOnMarkerDragListener(this)
        map.setOnMarkerClickListener(this)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, location.zoom))
    }
    override fun onMarkerDragStart(marker: Marker) {
    }

    override fun onMarkerDrag(marker: Marker) {
    }

    override fun onMarkerDragEnd(marker: Marker) {
        location.lat = marker.position.latitude
        location.lng = marker.position.longitude
        location.zoom = map.cameraPosition.zoom

    }

    // intercept the back button, and send it back to the parent activity:
    override fun onBackPressed() {
        val resultIntent = Intent()
        resultIntent.putExtra("location", location)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
        super.onBackPressed()
    }


    override fun onMarkerClick(marker: Marker): Boolean {
        val loc = LatLng(location.lat, location.lng)
        marker.setSnippet("GPS : " + loc.toString())
        return false
    }
}
