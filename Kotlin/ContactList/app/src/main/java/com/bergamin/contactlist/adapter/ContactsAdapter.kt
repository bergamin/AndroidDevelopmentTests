package com.bergamin.contactlist.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bergamin.contactlist.R
import com.bergamin.contactlist.model.Contact

/**
 * Created by Guilherme Taffarel Bergamin on 28/11/2017.
 */
class ContactsAdapter(
        private val context: Context,
        private val contacts: List<Contact>?): BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var contact = contacts!![position]
        var inflater = LayoutInflater.from(context)
        var view = convertView

        if(convertView == null){
            view = inflater.inflate(R.layout.list_item,null)
        }

        var itemName: TextView? = view?.findViewById(R.id.itemName)
        var itemPhone: TextView? = view?.findViewById(R.id.itemPhone)
        var itemPhoto: ImageView? = view?.findViewById(R.id.itemPhoto)
        var itemAddress: TextView? = view?.findViewById(R.id.itemAddress)
        var itemWebSite: TextView? = view?.findViewById(R.id.itemWebSite)

        itemName?.text = contact.name
        itemPhone?.text = contact.phone
        if(itemAddress != null) itemAddress.text = contact.address
        if(itemWebSite != null) itemWebSite.text = contact.webSite

        if(contact.photoPath != "") {
            var bitmap = BitmapFactory.decodeFile(contact.photoPath)
            bitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, true)
            itemPhoto?.setImageBitmap(bitmap)
            itemPhoto?.scaleType = ImageView.ScaleType.FIT_XY
        }
        return view!!
    }

    override fun getItem(position: Int): Any {
        return contacts!![position]
    }

    override fun getItemId(position: Int): Long {
        return contacts!![position].id
    }

    override fun getCount(): Int {
        return contacts?.size ?: 0
    }
}
