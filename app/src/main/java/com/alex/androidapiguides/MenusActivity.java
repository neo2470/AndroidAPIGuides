package com.alex.androidapiguides;

import android.os.Bundle;

/**
 * Created by alex on 15-4-12.
 * API Guides / User Interface / Menus
 */
public class MenusActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menus);
        changeTitle();
    }
}
