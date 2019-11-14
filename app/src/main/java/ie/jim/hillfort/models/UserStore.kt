package ie.jim.hillfort

import ie.jim.hillfort.models.UserModel

interface UserStore {
    fun findAll(): List<UserModel>
    fun create(user: UserModel)
    fun update(user: UserModel)
    fun delete(user: UserModel)
}


