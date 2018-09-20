package vn.edu.poly.contactdemo;

import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.contactdemo.adapter.ContactAdapter;
import vn.edu.poly.contactdemo.model.MyContact;

/**
 * Author : Huy Nguyen
 * Demo Load All Contacts
 */
public class ContactActivity extends AppCompatActivity {

    private Button btnLoadContacts;
    private RecyclerView lvList;
    private ContactAdapter contactAdapter;
    private List<MyContact> myContacts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contact);

        initViews();

        //initData();

        initActions();


    }

    private void initData() {
        myContacts = new ArrayList<>();

        for (int i = 0; i < 20; i++) {

            MyContact myContact = new MyContact();
            myContact.id = "" + i;
            myContact.name = "Huy Nguyen";
            myContacts.add(myContact);

        }

        contactAdapter = new ContactAdapter(this, myContacts);

        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this);

        lvList.setLayoutManager(linearLayoutManager);
        lvList.setAdapter(contactAdapter);


    }

    private void initActions() {

        btnLoadContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Duong dan or dia chi
                Uri uri = Uri.parse("content://contacts/people");
                // khoi tao 1 array rong de chua du lieu
                myContacts = new ArrayList<>();

                CursorLoader cursorLoader = new CursorLoader(ContactActivity.this, uri,
                        null, null,
                        null, null);

                Cursor cursor = cursorLoader.loadInBackground();

                cursor.moveToFirst();

                while (cursor.isAfterLast() == false) {

                    String id = cursor.getString(
                            cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cursor.getString(
                            cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    cursor.moveToNext();


                    MyContact myContact = new MyContact();
                    myContact.id = id;
                    myContact.name = name;

                    myContacts.add(myContact);

                }
                cursor.close();

                contactAdapter = new ContactAdapter(ContactActivity.this, myContacts);

                LinearLayoutManager linearLayoutManager =
                        new LinearLayoutManager(ContactActivity.this);

                lvList.setLayoutManager(linearLayoutManager);
                lvList.setAdapter(contactAdapter);


            }
        });


    }

    private void initViews() {

        btnLoadContacts = findViewById(R.id.btnLoadContacts);
        lvList = findViewById(R.id.lvList);

    }


}
