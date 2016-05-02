package com.bergamin.contactlist;

import android.widget.RatingBar;
import android.widget.TextView;

import com.bergamin.contactlist.model.Contact;

/**
 * Created by Guilherme on 02/04/2016.
 */
public class FormHelper {

    private final TextView nameTxt;
    private final TextView addressTxt;
    private final TextView phoneTxt;
    private final TextView websiteTxt;

    public FormHelper(FormActivity activity){
        nameTxt = (TextView) activity.findViewById(R.id.nameTxt);
        addressTxt = (TextView) activity.findViewById(R.id.addressTxt);
        phoneTxt = (TextView) activity.findViewById(R.id.phoneTxt);
        websiteTxt = (TextView) activity.findViewById(R.id.websiteTxt);
    }

    public Contact getContact(){

        Contact contact = new Contact();

        contact.setName(nameTxt.getText().toString());
        contact.setAddress(addressTxt.getText().toString());
        contact.setPhone(phoneTxt.getText().toString());
        contact.setWebSite(websiteTxt.getText().toString());

        return contact;
    }

}
