package com.alex.androidapiguides;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(position);
            }
        });
    }

    private void startActivity(int id) {
        Class act = null;
        switch (id) {
            case 0:
                act = LayoutsActivity.class;
                break;
            case 1:
                act = InputControlsActivity.class;
                break;
            case 2:
                act = ToastsActivity.class;
                break;
            case 3:
                act = MenusActivity.class;
                break;
            case 4:
                act = ActionBarActivity.class;
                break;
            case 5:
                act = SettingsActivity.class;
                break;
        }

        if (null == act) {
            Toast.makeText(this, getString(R.string.not_finished), Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, act);
            intent.putExtra(TOPIC, data[id]);
            startActivity(intent);
        }
    }

    public final static String TOPIC = "com.alex.androidapiguides.TOPIC";

    private final String[] data = {
            "Layouts",//0
            "Input Controls",//1
            "Toasts",//2
            "Menus",//3
            "Action Bar",//4
            "Settings",//5
            "Dialogs",//6
            "Notifications",//7
            "Search",//8
            "Accessibility",//9
            "Styles and Themes",//10
            "Custom Component"//11
    };
}
