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
    private Contact contact;

    public FormHelper(FormActivity activity){
        this.contact = new Contact();
        nameTxt = (TextView) activity.findViewById(R.id.nameTxt);
        addressTxt = (TextView) activity.findViewById(R.id.addressTxt);
        phoneTxt = (TextView) activity.findViewById(R.id.phoneTxt);
        websiteTxt = (TextView) activity.findViewById(R.id.websiteTxt);
    }

    public Contact getContact(){

        contact.setName(nameTxt.getText().toString());
        contact.setAddress(addressTxt.getText().toString());
        contact.setPhone(phoneTxt.getText().toString());
        contact.setWebSite(websiteTxt.getText().toString());

        return contact;
    }

    public void fillForm(Contact contact) {
        this.contact = contact;
        nameTxt.setText(contact.getName());
        addressTxt.setText(contact.getAddress());
        phoneTxt.setText(contact.getPhone());
        websiteTxt.setText(contact.getWebSite());
    }
}
