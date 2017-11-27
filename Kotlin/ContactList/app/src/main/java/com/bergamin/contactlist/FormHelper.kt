package com.bergamin.contactlist

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import android.widget.TextView
import com.bergamin.contactlist.model.Contact

/**
 * Created by gbergamin on 24/11/2017.
 */
class FormHelper(
        var activity: FormActivity,
        var contact: Contact) {

    val nameTxt: TextView
    val addressTxt: TextView
    val phoneTxt: TextView
    val websiteTxt: TextView
    val photoImg: ImageView

    init {
        nameTxt = activity.findViewById(R.id.nameTxt)
        addressTxt = activity.findViewById(R.id.addressTxt)
        phoneTxt = activity.findViewById(R.id.phoneTxt)
        websiteTxt = activity.findViewById(R.id.websiteTxt)
        photoImg = activity.findViewById(R.id.photoImg)

        contact.name = nameTxt.text.toString()
        contact.address = addressTxt.text.toString()
        contact.phone = phoneTxt.text.toString()
        contact.webSite = websiteTxt.text.toString()
        contact.photoPath = photoImg.tag as String
    }
    fun fillForm(contact: Contact){
        nameTxt.text = contact.name
        addressTxt.text = contact.address
        phoneTxt.text = contact.phone
        websiteTxt.text = contact.webSite

        loadImage(contact.photoPath)
        this.contact = contact
    }

    fun loadImage(path: String) {
        var bitmap = BitmapFactory.decodeFile(path)
        bitmap = Bitmap.createScaledBitmap(bitmap,300,300, true)

        photoImg.setImageBitmap(bitmap)
        photoImg.scaleType = ImageView.ScaleType.FIT_XY
        photoImg.tag = path
    }
}