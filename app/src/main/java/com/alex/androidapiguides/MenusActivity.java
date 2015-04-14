package com.alex.androidapiguides;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.alex.view.TitleBar;

/**
 * Created by alex on 15-4-12.
 * API Guides / User Interface / Menus
 */
public class MenusActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menus);

        Intent intent = getIntent();
        TitleBar titleBar = (TitleBar) findViewById(R.id.titleBar);
        titleBar.setTitle(intent.getStringExtra(MainActivity.TOPIC));

        textView = (TextView) findViewById(R.id.floatMenu);
        registerForContextMenu(textView);

        mActionModeCallback = new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.contextual_action_mode_1, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                String info = null;
                switch (item.getItemId()) {
                    case R.id.search:
                        info = "Search";
                        Toast.makeText(MenusActivity.this, info, Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.edit:
                        info = "Edit";
                        Toast.makeText(MenusActivity.this, info, Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.delete:
                        info = "Delete";
                        Toast.makeText(MenusActivity.this, info, Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                mActionMode = null;
            }
        };

        TextView individual = (TextView) findViewById(R.id.individual);
        individual.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mActionMode != null) {
                    return false;
                }

                mActionMode = startActionMode(mActionModeCallback);
                v.setSelected(true);
                return true;
            }
        });

        ListView listView = (ListView) findViewById(R.id.batch);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                Toast.makeText(MenusActivity.this, data[position] + " Checked: " + checked, Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.contextual_action_mode_2, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.share :
                        Toast.makeText(MenusActivity.this, "Do something", Toast.LENGTH_SHORT).show();
                        mode.finish();
                        return true;
                    default :
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String info = null;
        switch (item.getItemId()) {
            case R.id.search:
                info = "Search";
                Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.share:
                info = "Share";
                Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.file:
                info = "File";
                Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.newFile:
                info = "New";
                Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.edit:
                info = "Edit";
                Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.help:
                info = "Help";
                item.setChecked(!item.isChecked());
                Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.floating_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:
                textView.setText("Edited");
                return true;
            case R.id.share:
                textView.setText("Shared");
                return true;
            case R.id.delete:
                textView.setText("Deleted");
                return true;
            default:
                textView.setText("Floating Context Menu");
                return super.onContextItemSelected(item);
        }
    }

    public void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(MenusActivity.this, item.getItemId() + " was clicked!", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
                Toast.makeText(MenusActivity.this, "Popup Menu was dismissed!", Toast.LENGTH_SHORT).show();
            }
        });
        popupMenu.show();
    }

    private TextView textView;
    private ActionMode mActionMode;
    private ActionMode.Callback mActionModeCallback;
    private final String[] data = {
            "One",
            "Two",
            "Three",
            "Four",
            "Five",
            "Six",
            "Seven",
            "Eight",
            "Nine",
            "Ten"
    };
}
