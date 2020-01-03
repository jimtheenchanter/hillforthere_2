package ie.jim.hillfort.views

import android.content.Intent
import android.os.Parcelable
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

import com.google.firebase.auth.FirebaseAuth
import ie.jim.hillfort.R
import org.jetbrains.anko.AnkoLogger

import ie.jim.hillfort.models.HillfortModel
import ie.jim.hillfort.models.Location
import ie.jim.hillfort.views.editLocation.EditLocationView
import ie.jim.hillfort.views.hillfort.HillfortView
import ie.jim.hillfort.views.hillfortList.HillfortListView
import ie.jim.hillfort.views.login.LoginView
import ie.jim.hillfort.views.map.HillfortMapView

val IMAGE_REQUEST = 1
val LOCATION_REQUEST = 2

enum class VIEW {
    LOCATION, HILLFORT, MAPS, LIST, LOGIN
}

open abstract class BaseView() : AppCompatActivity(), AnkoLogger {
// declare the base presenter
    var basePresenter: BasePresenter? = null

    fun navigateTo(view: VIEW, code: Int = 0, key: String = "", value: Parcelable? = null) {
        var intent = Intent(this, HillfortListView::class.java)
        when (view) {
            VIEW.LOCATION -> intent = Intent(this, EditLocationView::class.java)
            VIEW.HILLFORT -> intent = Intent(this, HillfortView::class.java)
            VIEW.MAPS -> intent = Intent(this, HillfortMapView::class.java)
            VIEW.LIST -> intent = Intent(this, HillfortListView::class.java)
            VIEW.LOGIN -> intent = Intent(this, LoginView::class.java)
        }
        if (key != "") {
            intent.putExtra(key, value)
        }
        startActivityForResult(intent, code)
    }

    fun initPresenter(presenter: BasePresenter): BasePresenter {
        basePresenter = presenter
        return presenter
    }

    fun init(toolbar: Toolbar, upEnabled: Boolean) {
        toolbar.title = title
        setSupportActionBar(toolbar)


// find the users email and present it in the toolbar
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            toolbar.title = "${title}: ${user.email}"
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(upEnabled)
    }

// attempt to i mplement bottom_nav
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(
//            R.menu.bottom_nav_menu, menu)
//
//        return super.onCreateOptionsMenu(menu)
//    }


    override fun onDestroy() {
        basePresenter?.onDestroy()
        super.onDestroy()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            basePresenter?.doActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        basePresenter?.doRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    open fun showHillfort(hillfort: HillfortModel) {}
    open fun showHillforts(hillforts: List<HillfortModel>) {}
    open fun showProgress() {}
    open fun hideProgress() {}
    open fun showLocation(location : Location) {}
}