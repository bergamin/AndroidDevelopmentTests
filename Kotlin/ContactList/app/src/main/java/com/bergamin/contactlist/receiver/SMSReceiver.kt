package com.bergamin.contactlist.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.telephony.SmsMessage
import android.widget.Toast
import com.bergamin.contactlist.R
import com.bergamin.contactlist.dao.ContactDAO

/**
 * Created by Guilherme Taffarel Bergamin on 30/11/2017.
 */
class SMSReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val pdus = intent?.getSerializableExtra("pdus") as Array<*>
            val pdu = pdus[0] as ByteArray
            val format = intent.getSerializableExtra("format") as String
            val sms = SmsMessage.createFromPdu(pdu, format)
            val dao = ContactDAO(context!!)

            if(dao.isContact(sms.displayOriginatingAddress)){
                Toast.makeText(context,context.getString(R.string.new_sms_received),Toast.LENGTH_SHORT).show()
            }
        }
    }
}
