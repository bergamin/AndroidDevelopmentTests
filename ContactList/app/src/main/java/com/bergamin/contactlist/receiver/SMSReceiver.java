package com.bergamin.contactlist.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.bergamin.contactlist.R;
import com.bergamin.contactlist.dao.ContactDAO;

/**
 * Created by Guilherme on 20/09/2016.
 */
public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            Object[] pdus = (Object[]) intent.getSerializableExtra("pdus");
            byte[] pdu = (byte[]) pdus[0];
            String format = (String) intent.getSerializableExtra("format");
            SmsMessage sms = null;
            sms = SmsMessage.createFromPdu(pdu, format);
            ContactDAO dao = new ContactDAO(context);
            if (dao.isContact(sms.getDisplayOriginatingAddress())) {
                Toast.makeText(context, "You have a new SMS from a contact", Toast.LENGTH_SHORT).show();
                MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.msg);
                mediaPlayer.start();
            }
            dao.close();
        }
    }
}
