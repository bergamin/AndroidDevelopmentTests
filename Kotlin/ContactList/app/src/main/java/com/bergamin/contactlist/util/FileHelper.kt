package com.bergamin.contactlist.util

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Created by Guilherme Taffarel Bergamin on 23/12/2017.
 */
class FileHelper {
    fun getResourceTextByID(fileID: Int, context: Context): String? {
        val br = BufferedReader(InputStreamReader(context.resources.openRawResource(fileID)))
        val text = br.readText()
        br.close()
        return text
    }
}
