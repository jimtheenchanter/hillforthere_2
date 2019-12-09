package ie.jim.hillfort.views.hillfort

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import org.jetbrains.anko.toast
import ie.jim.hillfort.R // access resources
import ie.jim.hillfort.helpers.readImageFromPath
import ie.jim.hillfort.models.HillfortModel
import org.jetbrains.anko.AnkoLogger // allow console logging
import org.jetbrains.anko.info  // allow info windows
import ie.jim.hillfort.views.BaseView
import kotlinx.android.synthetic.main.activity_hillfort.*


class HillfortView : BaseView(), AnkoLogger {

    lateinit var presenter: HillfortPresenter
    var hillfort = HillfortModel()  // creating a hillfort as a class member:


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hillfort)
//        toolbarAdd.title = title
//        setSupportActionBar(toolbarAdd)
        init(toolbarAdd)
        info("Hillfort Activity initialized")

        presenter = initPresenter (HillfortPresenter(this)) as HillfortPresenter

//        btnAdd.setOnClickListener() {
//            if (hillfortName.text.toString().isEmpty()) { //checks that somethings has been entered for hillfortName
//                toast(R.string.hint_hillfortName)
//            } else {
//                presenter.doAddOrSave(hillfortName.text.toString(), description.text.toString())
//            }
//        }

        chooseImage.setOnClickListener { presenter.doSelectImage() }
        hillfortLocation.setOnClickListener { presenter.doSetLocation() }
    }


    override fun showHillfort(hillfort: HillfortModel) {
        hillfortName.setText(hillfort.title)
        description.setText(hillfort.description)
        hillfortImage.setImageBitmap(readImageFromPath(this, hillfort.image))
//        if (hillfort.image != null) {
            chooseImage.setText(R.string.change_image)
//        /}
//        btnAdd.setText(R.string.save_hillfort)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_hillfort, menu)
//        if (presenter.edit && menu != null) menu.getItem(1).setVisible(true)
        return super.onCreateOptionsMenu(menu)
    }


    // activity that checks that a button is pressed and reports back boolean
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_delete -> {
                presenter.doDelete()
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
}

