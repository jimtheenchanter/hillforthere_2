package ie.jim.hillfort.views.hillfort

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import ie.jim.hillfort.R
import ie.jim.hillfort.helpers.readImageFromPath
import ie.jim.hillfort.models.HillfortModel
import ie.jim.hillfort.views.BaseView
import kotlinx.android.synthetic.main.activity_hillfort.*
//import kotlinx.android.synthetic.main.activity_hillfort.description
//import kotlinx.android.synthetic.main.card_hillfort.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast



class HillfortView : BaseView(), AnkoLogger {

    lateinit var presenter: HillfortPresenter
//    lateinit var map: GoogleMap
    var hillfort = HillfortModel()  // creating a hillfort as a class member:


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hillfort)
        super.init(toolbarAdd, true)
        info("Hillfort Activity initialized")
        presenter = initPresenter (HillfortPresenter(this)) as HillfortPresenter
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync {
            presenter.doConfigureMap(it)
            it.setOnMapClickListener { presenter.doSetLocation() }
        }
        chooseImage.setOnClickListener { presenter.doSelectImage() }
//        hillfortLocation.setOnClickListener { presenter.doSetLocation() }
    }

    override fun showHillfort(hillfort: HillfortModel) {
        hillfortName.setText(hillfort.title)
        description.setText(hillfort.description)
        hillfortImage.setImageBitmap(readImageFromPath(this, hillfort.image))
        if (hillfort.image != null) {
            chooseImage.setText(R.string.change_image)
        }
        lat.setText("%.6f".format(hillfort.lat))
        lng.setText("%.6f".format(hillfort.lng))
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_hillfort, menu)
       if (presenter.edit && menu != null) menu.getItem(1).setVisible(true)
        return super.onCreateOptionsMenu(menu)
    }

    // activity that checks that a button is pressed and reports back boolean
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_delete -> {
                presenter.doDelete()
                finish()
            }
            R.id.item_save -> {
                if (hillfortName.text.toString().isEmpty()) {
                    toast(R.string.hint_hillfortName)
                } else {
                    presenter.doAddOrSave(hillfortName.text.toString(), description.text.toString())
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            presenter.doActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onBackPressed() {
        presenter.doCancel()
    }
    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
        presenter.doRestartLocationUpdates()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }
}


