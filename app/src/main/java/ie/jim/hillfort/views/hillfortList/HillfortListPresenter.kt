package ie.jim.hillfort.views.hillfortList

import ie.jim.hillfort.models.HillfortModel
import ie.jim.hillfort.views.BasePresenter
import ie.jim.hillfort.views.BaseView
import ie.jim.hillfort.views.VIEW


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
        view?.showHillforts(app.hillforts.findAll())
    }
}