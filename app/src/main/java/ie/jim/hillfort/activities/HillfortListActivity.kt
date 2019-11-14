package ie.jim.hillfort.activities

import HillfortAdapter
import HillfortListener
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.recyclerview.widget.LinearLayoutManager
import ie.jim.hillfort.R
import ie.jim.hillfort.main.MainApp
import ie.jim.hillfort.models.HillfortModel
import kotlinx.android.synthetic.main.activity_hillfort_list.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult

class HillfortListActivity : AppCompatActivity(), AnkoLogger, HillfortListener {

    lateinit var app: MainApp // Retrieve and store a reference to the MainApp object

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hillfort_list)
        app = application as MainApp
        toolbar.title = title   //  enable the action bar (and give it a title)
        setSupportActionBar(toolbar)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        loadHillforts()

    }


    private fun loadHillforts() {
        showHillforts(app.hillforts.findAll())
    }

    fun showHillforts (hillforts: List<HillfortModel>) {
        recyclerView.adapter = HillfortAdapter(hillforts, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_add -> startActivityForResult<HillfortActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }


    // pass the selected hillfort to the activity - this is enabled via the @parcelable mechanism
    override fun onHillfortClick(hillfort: HillfortModel) {
        startActivityForResult(intentFor<HillfortActivity>().putExtra("hillfort_edit", hillfort), 0)
    }


    //method to refresh model on the view to display new Hillfort
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        recyclerView.adapter?.notifyDataSetChanged() // instruct the recyclerView's adapter that the model has been updated
        loadHillforts()
       super.onActivityResult(requestCode, resultCode, data)
    }

}

