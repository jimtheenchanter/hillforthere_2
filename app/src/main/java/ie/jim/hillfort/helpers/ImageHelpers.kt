package ie.jim.hillfort.helpers

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.provider.MediaStore
import ie.jim.hillfort.R
import java.io.IOException

// Function to show an image picker dialog.
fun showImagePicker(parent: Activity, id: Int) {
    val intent = Intent()
    intent.type = "image/*" // Looking for an image
    intent.action = Intent.ACTION_OPEN_DOCUMENT // to open
    intent.addCategory(Intent.CATEGORY_OPENABLE) // show the categories of images available on device
    val chooser = Intent.createChooser(intent, R.string.select_hillfort_image.toString())
    parent.startActivityForResult(chooser, id) // run chooser bit and check id
                                               // matches to conirm task completion
}

// function to display the image
fun readImage(activity: Activity, resultCode: Int, data: Intent?): Bitmap? {
    var bitmap: Bitmap? = null
    if (resultCode == Activity.RESULT_OK && data != null && data.data != null) {
        try {
           bitmap = MediaStore.Images.Media.getBitmap(activity.contentResolver, data.data)

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    return bitmap
}

fun readImageFromPath(context: Context, path : String) : Bitmap? {
    var bitmap : Bitmap? = null
    val uri = Uri.parse(path)
    if (uri != null) {
        try {
            val parcelFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r")
            val fileDescriptor = parcelFileDescriptor?.getFileDescriptor()
            bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor)
            parcelFileDescriptor?.close()
        } catch (e: Exception) {
        }
    }
    return bitmap
}
