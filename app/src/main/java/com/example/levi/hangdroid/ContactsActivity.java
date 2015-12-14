package com.example.levi.hangdroid;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ContactsActivity extends ListActivity
    {
    ListView listView;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);

        //sets the appropriate layout
        setContentView(R.layout.activity_contacts);

        //queries all of the phone contacts
        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        startManagingCursor(cursor);

        //assigns the phone contact and phone number to contacts menu
        final String[] Texter = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};

        //assigns text1 as the contact name and text2 as contact number
        int[] item = {android.R.id.text1, android.R.id.text2};

        //makes a list using the two text fields above
        SimpleCursorAdapter listadapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, Texter, item, 0);

        setListAdapter(listadapter);

        //displays the list of contacts
        listView = getListView();
        listView.setChoiceMode(listView.CHOICE_MODE_MULTIPLE);

        //listens for the contact that was clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
        @Override
        public void onItemClick(AdapterView <?> parent, View view, int position, long id)
            {
            //assigns the contact name to a string from the text1 field
            String selectName = ((TextView) (listView.findViewById(android.R.id.text1))).getText().toString();

            //assigns the contact number to a string from the text2 field
            String selectPhone = ((TextView) (listView.findViewById(android.R.id.text2))).getText().toString();

            Log.d("MYLOG", "onClick: " + position + "/" + id + "/" + selectPhone);

            Intent intent = new Intent(ContactsActivity.this, TextActivity.class);

            intent.putExtra("Phone", selectPhone);

            intent.putExtra("Name", selectName);

            startActivity(intent);
            }
        });
        }
    }
