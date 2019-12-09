package ie.jim.hillfort.views.hillfort



import android.content.Intent
import org.jetbrains.anko.intentFor
import ie.jim.hillfort.helpers.showImagePicker
import ie.jim.hillfort.main.MainApp
import ie.jim.hillfort.models.Location
import ie.jim.hillfort.models.HillfortModel
import ie.jim.hillfort.views.*
import ie.jim.hillfort.views.editLocation.EditLocationView

class HillfortPresenter(view: BaseView) : BasePresenter(view) {

//    val IMAGE_REQUEST = 1
//    val LOCATION_REQUEST = 2

    var hillfort = HillfortModel()
    var defaultLocation = Location(52.245696, -7.139102, 15f)
//    var app: MainApp
    var edit = false;

    init {
//        app = view.application as MainApp
        if (view.intent.hasExtra("hillfort_edit")) {
            edit = true
            hillfort = view.intent.extras?.getParcelable<HillfortModel>("hillfort_edit")!!
            view.showHillfort(hillfort)
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
        app.hillforts.delete(hillfort)
        view?.finish()
    }

    fun doSelectImage() {
        showImagePicker(view!!, IMAGE_REQUEST)
    }

    fun doSetLocation() {
        if (edit == false) {
            view?.navigateTo(VIEW.LOCATION, LOCATION_REQUEST, "location", defaultLocation)
        } else {
            view?.navigateTo(
                VIEW.LOCATION,
                LOCATION_REQUEST,
                "location",
                Location(hillfort.lat, hillfort.lng, hillfort.zoom)
            )
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
            }
        }
    }
}

