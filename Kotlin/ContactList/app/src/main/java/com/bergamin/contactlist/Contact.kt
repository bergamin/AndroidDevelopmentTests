package com.bergamin.contactlist

import java.io.Serializable

/**
 * Created by gbergamin on 24/11/2017.
 */
class Contact(
        var id: Long,
        var name: String,
        var address: String,
        var phone: String,
        var webSite: String,
        var photoPath: String): Serializable {

    override fun toString(): String {
        return name
    }
}