package com.bergamin.contactlist.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bergamin.contactlist.ContactListActivity;
import com.bergamin.contactlist.R;
import com.bergamin.contactlist.model.Contact;

import java.util.List;

/**
 * Created by Guilherme on 20/08/2016.
 */
public class ContactsAdapter extends BaseAdapter {
    private final List<Contact> contacts;
    private final Context context;

    public ContactsAdapter(Context context, List<Contact> contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return contacts.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Contact contact = contacts.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, null);
        }

        TextView nameTxt = (TextView) convertView.findViewById(R.id.item_name);
        TextView phoneTxt = (TextView) convertView.findViewById(R.id.item_phone);
        ImageView photoImg = (ImageView) convertView.findViewById(R.id.item_photo);

        nameTxt.setText(contact.getName());
        phoneTxt.setText(contact.getPhone());

        String filePath = contact.getPhotoPath();
        if(filePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(filePath);
            bitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
            photoImg.setImageBitmap(bitmap);
            photoImg.setScaleType(ImageView.ScaleType.FIT_XY);
        }

        return convertView;
    }
}
