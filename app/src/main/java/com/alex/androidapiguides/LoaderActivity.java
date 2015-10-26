package com.alex.androidapiguides;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * Created by alex on 15-10-26.
 */
public class LoaderActivity extends BaseActivity implements LoaderManager.LoaderCallbacks<Cursor>, SearchView.OnQueryTextListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadder);

        mAdapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_2,
                null,
                new String[] {ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.CONTACT_STATUS},
                new int[] {android.R.id.text1, android.R.id.text2}
        );

        mListView = (ListView) findViewById(R.id.listView);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(LoaderActivity.this, "Item clicked : " + position, Toast.LENGTH_SHORT).show();
            }
        });

        SearchView searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(this);

        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mCurFilter = !TextUtils.isEmpty(newText) ? newText : null;
        getLoaderManager().restartLoader(0, null, this);
        return true;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d("Debug", "onCreateLoader()");

        Uri baseUri;
        if(null != mCurFilter) {
            baseUri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_FILTER_URI, Uri.encode(mCurFilter));
        } else {
            baseUri = ContactsContract.Contacts.CONTENT_URI;
        }

        final String select = "((" + ContactsContract.Contacts.DISPLAY_NAME + " NOTNULL) AND ("
                + ContactsContract.Contacts.HAS_PHONE_NUMBER + "=1) AND ("
                + ContactsContract.Contacts.DISPLAY_NAME + " != '' ))";

        return new CursorLoader(
                this,
                baseUri,
                CONTACTS_SUMMARY_PROJECTION,
                select,
                null,
                ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC"
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.d("Debug", "onLoadFinished()");
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Log.d("Debug", "onLoaderReset()");
        mAdapter.swapCursor(null);
    }

    private final String[] CONTACTS_SUMMARY_PROJECTION = new String[] {
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.Contacts.CONTACT_STATUS,
            ContactsContract.Contacts.CONTACT_PRESENCE,
            ContactsContract.Contacts.PHOTO_ID,
            ContactsContract.Contacts.LOOKUP_KEY,
    };
    private SimpleCursorAdapter mAdapter;
    private ListView mListView;
    private String mCurFilter;
}
