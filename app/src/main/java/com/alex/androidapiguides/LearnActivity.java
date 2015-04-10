package com.alex.androidapiguides;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

/**
 * Created by alex on 15-4-10.
 * hold all of the fragments.
 */
public class LearnActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_learn);

        Intent intent = getIntent();
        Log.d("Debug", intent.getStringExtra("topic"));
    }
}
