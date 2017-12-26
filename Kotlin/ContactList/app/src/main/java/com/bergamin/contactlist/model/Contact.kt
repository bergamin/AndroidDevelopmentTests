package com.bergamin.contactlist.model

import java.io.Serializable

/**
 * Created by Guilherme Taffarel Bergamin on 24/11/2017.
 */
class Contact(
        var id: Long = 0,
        var name: String = "",
        var address: String = "",
        var phone: String = "",
        var webSite: String = "",
        var photoPath: String = ""
        ): Serializable {

    override fun toString(): String {
        return name
    }
}
