package com.bergamin.contactlist.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import android.widget.TextView
import com.bergamin.contactlist.R
import com.bergamin.contactlist.activity.FormActivity
import com.bergamin.contactlist.model.Contact

/**
 * Created by Guilherme Taffarel Bergamin on 24/11/2017.
 */
class FormHelper(
        activity: FormActivity) {

    private val nameTxt: TextView
    private val addressTxt: TextView
    private val phoneTxt: TextView
    private val websiteTxt: TextView
    private val photoImg: ImageView
    var contact: Contact
        get() {
            field.name = nameTxt.text.toString()
            field.address = addressTxt.text.toString()
            field.phone = phoneTxt.text.toString()
            field.webSite = websiteTxt.text.toString()
            field.photoPath = photoImg.tag as String? ?: ""

            return field
        }

    init {
        contact = Contact()
        nameTxt = activity.findViewById(R.id.nameTxt)
        addressTxt = activity.findViewById(R.id.addressTxt)
        phoneTxt = activity.findViewById(R.id.phoneTxt)
        websiteTxt = activity.findViewById(R.id.websiteTxt)
        photoImg = activity.findViewById(R.id.photoImg)
    }

    fun fillForm(contact: Contact){
        nameTxt.text = contact.name
        addressTxt.text = contact.address
        phoneTxt.text = contact.phone
        websiteTxt.text = contact.webSite

        loadImage(contact.photoPath)
        this.contact = contact
    }

    fun loadImage(path: String?) {
        if(path != null && path != "") {
            var bitmap = BitmapFactory.decodeFile(path)
            bitmap = Bitmap.createScaledBitmap(bitmap, 300, 300, true)

            photoImg.setImageBitmap(bitmap)
            photoImg.scaleType = ImageView.ScaleType.FIT_XY
            photoImg.tag = path
        }
    }
}
