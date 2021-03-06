package com.bergamin.contactlist.activity

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.bergamin.contactlist.BuildConfig
import com.bergamin.contactlist.util.FormHelper
import com.bergamin.contactlist.R
import com.bergamin.contactlist.dao.ContactDAO
import com.bergamin.contactlist.model.Contact
import kotlinx.android.synthetic.main.activity_form.*
import java.io.File

/**
 * Created by Guilherme Taffarel Bergamin on 23/11/2017.
 */
class FormActivity : AppCompatActivity() {

    private var helper: FormHelper? = null
    private var photoPath: String? = null

    companion object {
        val CAMERA_REQUEST = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        helper = FormHelper(this)

        var intent = intent
        val contact = intent.getSerializableExtra("contact") as Contact?
        if(contact != null) {
            helper?.fillForm(contact)
        }

        photoBtn.setOnClickListener {
            photoPath = getExternalFilesDir(null).path + "/" + System.currentTimeMillis() + ".jpg"
            val uri = FileProvider.getUriForFile(this@FormActivity,BuildConfig.APPLICATION_ID + ".provider",File(photoPath))

            intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)

            startActivityForResult(intent, CAMERA_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK){
            helper?.loadImage(photoPath!!)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.form_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.saveBtn -> {
                val contact = helper?.contact
                val dao = ContactDAO(this)

                if(contact!!.id > 0){
                    dao.update(contact)
                } else {
                    dao.insert(contact)
                }

                dao.close()
                Toast.makeText(this@FormActivity, getString(R.string.contact) + " " + contact.name + " " + getString(R.string.saved),Toast.LENGTH_SHORT).show()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
