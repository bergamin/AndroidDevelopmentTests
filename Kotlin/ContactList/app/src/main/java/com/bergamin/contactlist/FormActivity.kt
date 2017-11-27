package com.bergamin.contactlist

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.bergamin.contactlist.dao.ContactDAO
import com.bergamin.contactlist.model.Contact
import java.io.File

class FormActivity : AppCompatActivity(), View.OnClickListener {

    var helper: FormHelper? = null
    var photoPath: String? = null

    companion object {
        val CAMERA_REQUEST = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        helper?.activity = this

        var contact: Contact = intent.getSerializableExtra("contact") as Contact

        helper?.fillForm(contact)

    }
    override fun onClick(view: View?) {
        photoPath = getExternalFilesDir(null).path + "/" + System.currentTimeMillis() + ".jpg"

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        var photoFile = File(photoPath)

        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile))
        startActivityForResult(intent,CAMERA_REQUEST)
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
                var contact = helper?.contact
                var dao = ContactDAO(this)

                if(contact!!.id > 0){
                    dao.update(contact)
                } else {
                    dao.insert(contact)
                }

                dao.close()
                Toast.makeText(this, getString(R.string.contact) + " " + contact.name + " " + getString(R.string.saved),Toast.LENGTH_SHORT).show()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
