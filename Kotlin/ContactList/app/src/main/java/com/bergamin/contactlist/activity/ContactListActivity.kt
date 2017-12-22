package com.bergamin.contactlist.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.bergamin.contactlist.R
import com.bergamin.contactlist.adapter.ContactsAdapter
import com.bergamin.contactlist.dao.ContactDAO
import com.bergamin.contactlist.model.Contact
import com.bergamin.contactlist.util.SendContactsTask
import kotlinx.android.synthetic.main.activity_contact_list.*

class ContactListActivity : AppCompatActivity() {

    companion object{
        val PHONE_CALL_REQUEST = 1
        val SMS_RECEIVED_REQUEST = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)

        newContactBtn.setOnClickListener {
            intent.setClass(this, FormActivity.javaClass)
            startActivity(intent)
        }

        registerForContextMenu(contactsLV)
        contactsLV.setOnItemClickListener { _, _, position, _ ->
            var contact = contactsLV.getItemAtPosition(position)

            intent.setClass(this, FormActivity.javaClass)
            intent.putExtra("contact",Array(1,{contact}))
            startActivity(intent)
        }
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,Array(1,{Manifest.permission.RECEIVE_SMS}), SMS_RECEIVED_REQUEST)
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        var info = menuInfo as AdapterView.AdapterContextMenuInfo
        val contact = contactsLV.getItemAtPosition(info.position) as Contact

        var miCall = menu?.add(R.string.call)
        miCall?.setOnMenuItemClickListener {
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,Array(1,{Manifest.permission.CALL_PHONE}), PHONE_CALL_REQUEST)
            } else {
                intent.action = Intent.ACTION_CALL
                intent.data = Uri.parse("tel:" + contact.phone)
                startActivity(intent)
            }
            return@setOnMenuItemClickListener false
        }

        var miFindInMap = menu?.add(R.string.map)
        intent.action = Intent.ACTION_VIEW
        intent.data = Uri.parse("geo:0,0?q=" + contact.address)
        miFindInMap?.intent = intent

        var miSendSMS = menu?.add(R.string.sms)
        intent.action = Intent.ACTION_VIEW
        intent.data = Uri.parse("sms:" + contact.phone)
        miSendSMS?.intent = intent

        var miWebSite = menu?.add(R.string.website)
        var webSite = contact.webSite
        if(!webSite.startsWith("http://") && !webSite.startsWith("https://")){
            webSite = "http://" + webSite
        }
        intent.action = Intent.ACTION_VIEW
        intent.data = Uri.parse(webSite)
        miWebSite?.intent = intent

        var miDelete = menu?.add(R.string.delete)
        miDelete?.setOnMenuItemClickListener {
            var dao = ContactDAO(this)

            dao.delete(contact)
            dao.close()
            loadList()

            Toast.makeText(this,contact.name + " " + getString(R.string.deleted),Toast.LENGTH_SHORT).show()
            return@setOnMenuItemClickListener false
        }
    }
    override fun onResume() {
        loadList()
        super.onResume()
    }
    fun loadList() {
        var dao = ContactDAO(this)
        var contacts = dao.getContacts()
        dao.close()
        contactsLV.adapter = ContactsAdapter(this,contacts)
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == PHONE_CALL_REQUEST){
            TODO("check if permission was granted and then, perform call")
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.contact_list_menu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.menuBackupContacts -> {
                SendContactsTask(this).execute()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
