package ie.jim.hillfort.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

// implement a parcelize capability.
@Parcelize
data class UserModel (
    var id: Long = 0,
    var email: String = "",
    var password: String = "",
    var visitedforts: Int = 0)
    : Parcelable
