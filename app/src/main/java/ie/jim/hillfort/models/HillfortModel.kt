package ie.jim.hillfort.models

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

// implement a parcelize capability.
@Parcelize
@Entity
data class HillfortModel(@PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var fbId : String = "",
    var title: String = "",
    var description: String = "",
    var image: String = "",
    var favourite: Boolean = false,
//    var rating: Int = 0,
   @Embedded var location : Location = Location()): Parcelable

@Parcelize
data class Location(var lat: Double = 0.0,
                    var lng: Double = 0.0,
                    var zoom: Float = 0f) : Parcelable