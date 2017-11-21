package com.bergamin.contactlist;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bergamin.contactlist.dao.ContactDAO;
import com.bergamin.contactlist.model.Contact;

import java.io.File;
import java.math.BigInteger;

/**
 * Created by Guilherme on 28/03/2016.
 */
public class FormActivity extends AppCompatActivity {

    public static final int CAMERA_REQUEST = 1;

    private FormHelper helper;
    private String filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_layout);
        helper = new FormHelper(this);

        Intent intent = getIntent();
        Contact contact = (Contact) intent.getSerializableExtra("contact");

        if(contact != null){
            helper.fillForm(contact);
        }

        Button photoBtn = (Button) findViewById(R.id.photoBtn);
        photoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                filePath = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                File photoFile = new File(filePath);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                startActivityForResult(cameraIntent,CAMERA_REQUEST);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK){
            helper.loadImage(filePath);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.form_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.saveBtn:

                Contact contact = helper.getContact();
                ContactDAO dao = new ContactDAO(this);

                if(contact.getId() != null){
                    dao.update(contact);
                }else {
                    dao.insert(contact);
                }

                dao.close();

                Toast.makeText(FormActivity.this, getString(R.string.contact) + " " + contact.getName() + " " + getString(R.string.saved), Toast.LENGTH_SHORT).show();
                finish();

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
