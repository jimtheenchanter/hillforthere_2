package ie.jim.hillfort.views.hillfortList

import com.google.firebase.auth.FirebaseAuth
import ie.jim.hillfort.models.HillfortModel
import ie.jim.hillfort.views.BasePresenter
import ie.jim.hillfort.views.BaseView
import ie.jim.hillfort.views.VIEW
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class HillfortListPresenter(view: BaseView) : BasePresenter(view){


    fun doAddHillfort() {
        view?.navigateTo(VIEW.HILLFORT)
    }

    fun doEditHillfort(hillfort: HillfortModel) {
        view?.navigateTo(VIEW.HILLFORT, 0, "hillfort_edit", hillfort)
    }

    fun doShowHillfortsMap() {
        view?.navigateTo(VIEW.MAPS)
    }


    fun loadHillforts() {
        doAsync {
            val hillforts = app.hillforts.findAll()
            uiThread {
                view?.showHillforts(hillforts)
            }
        }
    }

    fun doLogout() {
        FirebaseAuth.getInstance().signOut()
        view?.navigateTo(VIEW.LOGIN)
    }
}