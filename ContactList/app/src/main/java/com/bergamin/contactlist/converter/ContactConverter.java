package com.bergamin.contactlist.converter;

import com.bergamin.contactlist.model.Contact;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

/**
 * Created by Guilherme on 30/10/2016.
 */
public class ContactConverter {
    public String convertoToJSON(List<Contact> contacts) {
        JSONStringer js = new JSONStringer();
        try {
            js.object().key("List").array().object().key("Contact").array();
            for(Contact contact:contacts){
                js.object();
                js.key("id").value(contact.getId());
                js.key("name").value(contact.getName());
                js.key("address").value(contact.getAddress());
                js.key("phone").value(contact.getPhone());
                js.key("webSite").value(contact.getWebSite());
                js.endObject();
            }
            js.endArray().endObject().endArray().endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return js.toString();
    }
}
