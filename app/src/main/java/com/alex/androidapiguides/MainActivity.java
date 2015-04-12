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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
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
        }

        if (null == act) {
            Toast.makeText(this, getString(R.string.not_finished), Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, act);
            intent.putExtra("topic", data[id]);
            startActivity(intent);
        }
    }

    private final String[] data = {
            "Layouts",
            "Input Controls",
            "Toasts",
            "Menus",
            "Action Bars",
            "Settings",
            "Dialogs",
            "Notifications",
            "Search",
            "Accessibility",
            "Styles and Themes",
            "Custom Component"
    };
}
