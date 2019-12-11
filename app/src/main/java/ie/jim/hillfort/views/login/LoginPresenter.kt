package ie.jim.hillfort.views.login

import  ie.jim.hillfort.views.BasePresenter
import  ie.jim.hillfort.views.BaseView
import  ie.jim.hillfort.views.VIEW

class LoginPresenter(view: BaseView) : BasePresenter(view) {

    fun doLogin(email: String, password: String) {
        view?.navigateTo(VIEW.LIST)
    }

    fun doSignUp(email: String, password: String) {
        view?.navigateTo(VIEW.LIST)
    }
}