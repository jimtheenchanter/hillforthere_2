package ie.jim.hillfort.models.json

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import ie.jim.hillfort.UserStore
import org.jetbrains.anko.AnkoLogger
import ie.jim.hillfort.helpers.*
import ie.jim.hillfort.models.UserModel
import java.util.*

val JSON_FILE_user = "users.json"
val gsonBuilder_user = GsonBuilder().setPrettyPrinting().create()
val listType_user = object : TypeToken<java.util.ArrayList<UserModel>>() {}.type

fun generateRandomUserId(): Long {
    return Random().nextLong()
}

class UserJSONStore : UserStore, AnkoLogger {

    val context: Context
    var users = mutableListOf<UserModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE_user)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<UserModel> {
        return users
    }

    override fun create(user: UserModel) {
        user.id = generateRandomUserId()
        users.add(user)
        serialize()
    }


    override fun update(user: UserModel) {
        val usersList = findAll() as ArrayList<UserModel>
        var foundUser: UserModel? = usersList.find { p -> p.id == user.id }
        if (foundUser != null) {
            foundUser.id = user.id
            foundUser.email = user.email
            foundUser.password = user.password
            foundUser.visitedforts = user.visitedforts

        }
        serialize()
    }

    override fun delete(user: UserModel) {
        users.remove(user)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder_user.toJson(users,
            listType_user
        )
        write(context, JSON_FILE_user, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context,
            JSON_FILE_user
        )
        users = Gson().fromJson(jsonString,
            listType_user
        )
    }


}